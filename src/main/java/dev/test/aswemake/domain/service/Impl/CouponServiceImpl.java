package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.controller.dto.request.PayProductRequest;
import dev.test.aswemake.domain.controller.dto.request.coupon.CouponCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.PayCouponInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.coupon.Coupon;
import dev.test.aswemake.domain.entity.enums.OrderStatus;
import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.repository.CouponRepository;
import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.repository.ProductRepository;
import dev.test.aswemake.domain.service.CouponService;
import dev.test.aswemake.domain.service.OrderCalculatorWithCoupon;
import dev.test.aswemake.domain.service.ProductService;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.coupon.NotFoundCouponId;
import dev.test.aswemake.global.exception.member.NotFoundMemberId;
import dev.test.aswemake.global.exception.order.NotFoundOrderId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final ProductService productService;
    private final OrderCalculatorWithCoupon orderCalculatorWithCoupon;

    public CouponServiceImpl(
            CouponRepository couponRepository,
            MemberRepository memberRepository,
            ProductService productService,
            OrderCalculatorWithCoupon orderCalculatorWithCoupon
    ) {
        this.couponRepository = couponRepository;
        this.memberRepository = memberRepository;
        this.productService = productService;
        this.orderCalculatorWithCoupon = orderCalculatorWithCoupon;
    }

    /**
     * 쿠폰을 생성한다.
     * 실제 서비스를 생각하면 관리자 또는 마켓의 권한을 가지는 유저가 생성을 하는게 맞다.
     * 하지만 이렇게 하면 추가적인 단계가 필요하기 때문에 테스트의 편의성을 위해서 USER, MARKET 모두 쿠폰이 생성이 가능하다.
     *
     * @param loginUserDto        로그인 회원의 정보
     * @param couponCreateRequest 쿠폰 이름, 가격, 전략을 입력한다.
     * @throws NotFoundMemberId loginUserDto.getMemberId()
     */
    @Override
    @Transactional
    public void createCoupon(LoginUserDto loginUserDto, CouponCreateRequest couponCreateRequest) {
        Member member = memberRepository.findById(loginUserDto.getMemberId())
                .orElseThrow(() -> new NotFoundMemberId(loginUserDto.getMemberId()));

        couponRepository.save(Coupon.builder()
                .couponName(couponCreateRequest.getCouponName())
                .salePrice(couponCreateRequest.getSalePrice())
                .couponSaleStrategy(couponCreateRequest.getCouponSaleStrategy())
                .member(member)
                .build());
    }


    /**
     * 주문을 쿠폰을 이용하여 결제한다.
     * 주문의 상태를 COMPLETE로 변경하며 상품의 수량은 감소한다.
     * 이때 Dirty Checking을 하면 N+1 문제가 발생을 한다. -> Modifying
     * @param loginUserDto 로그인 사용자 정보
     * @param payProductRequest 쿠폰 아이디, 주문 아이디
     * @return PayCouponInfoResponse
     * @throws NotFoundMemberId
     * @throws NotFoundCouponId
     * @throws NotFoundOrderId
     *
     */
    @Override
    @Transactional
    public PayCouponInfoResponse processPaymentWithCoupon(LoginUserDto loginUserDto, PayProductRequest payProductRequest) {
        Member member = memberRepository.findByIdWithCoupon(loginUserDto.getMemberId())
                .orElseThrow(() -> new NotFoundMemberId(loginUserDto.getMemberId()));

        Coupon coupon = member.getCoupons().stream()
                .filter(c -> Objects.equals(c.getId(), payProductRequest.getCouponId()))
                .findAny().orElseThrow(() -> new NotFoundCouponId(payProductRequest.getCouponId()));

        Order orders = member.getOrders().stream()
                .filter(order -> order.getId().equals(payProductRequest.getOrderId()))
                .findAny().orElseThrow(() -> new NotFoundOrderId(payProductRequest.getOrderId()));

        List<OrderItem> orderItems = orders.getOrderItems();

        // 특정 상품
        List<Product> productList = orderItems.stream()
                .map(OrderItem::getProduct)
                .filter(Product::isCouponUseStatus)
                .collect(Collectors.toList());

        List<ProductOrderResponse> productOrderResponses = orderItems.stream()
                .map(ProductOrderResponse::createProductOrderResponse)
                .collect(Collectors.toList());

        orders.setOrderStatus(OrderStatus.COMPLETE);

        // Modifying 처리
        productService.declineProductQuantity(orderItems);


        int calculateSpecificTotalPrice = orderCalculatorWithCoupon.calculateSpecificTotalPrice(orders, coupon);
        int calculateTotalPrice = orderCalculatorWithCoupon.calculateTotalPrice(orders, coupon);

        /**
         *  if (!productList.isEmpty()) : 특정 상품에 대한 로직을 처리한다. 이때 Coupon의 전략에 따라서 값을 처리한다.
         *  1. 고정
         *  - 특정 상품에 쿠폰의 할인 Price를 적용하여 전체적인 로직을 처리한다.
         *  2. 비율
         *  - 특정 상품을 제외하고 일반 상품에 대해서 비율로 할인가를 처리한다.
         *
         *  else : 장바구니에 특정 상품이 없는 경우를 처리한다.
         *  1. 고정
         *  - 전체 상품의 금액에 - price를 하여 로직을 처리한다. 이때 배달비는 별로도 할인가를 적용하지 않는다.
         *  2. 비율
         *  - 전체 상품에 비율로 할인가를 계산한다.
         */
        if (!productList.isEmpty()) {
            return PayCouponInfoResponse.SPECIFIC(productOrderResponses, calculateSpecificTotalPrice, coupon);
        } else {
            return PayCouponInfoResponse.TOTAL(productOrderResponses, calculateTotalPrice, coupon);
        }
    }
}
