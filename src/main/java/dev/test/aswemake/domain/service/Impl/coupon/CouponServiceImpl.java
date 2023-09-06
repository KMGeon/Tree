package dev.test.aswemake.domain.service.Impl.coupon;

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
import dev.test.aswemake.domain.service.CouponService;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.member.NotFoundMemberId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final OrderCalculatorWithCoupon orderCalculatorWithCoupon;

    public CouponServiceImpl(
            CouponRepository couponRepository,
            MemberRepository memberRepository,
            OrderCalculatorWithCoupon orderCalculatorWithCoupon
    ) {
        this.couponRepository = couponRepository;
        this.memberRepository = memberRepository;
        this.orderCalculatorWithCoupon = orderCalculatorWithCoupon;
    }

    @Override
    @Transactional
    public void createCoupon(LoginUserDto loginUserDto, CouponCreateRequest couponCreateRequest) {
        Member member = memberRepository.findById(loginUserDto.getMemberId())
                .orElseThrow(()->new NotFoundMemberId(loginUserDto.getMemberId()));

        couponRepository.save(Coupon.builder()
                .couponName(couponCreateRequest.getCouponName())
                .salePrice(couponCreateRequest.getSalePrice())
                .couponSaleStrategy(couponCreateRequest.getCouponSaleStrategy())
                .member(member)
                .build());
    }


    @Override
    @Transactional
    public PayCouponInfoResponse payProduct(LoginUserDto loginUserDto, PayProductRequest payProductRequest) {

        Member member = memberRepository.findById(loginUserDto.getMemberId())
                .orElseThrow();

        Coupon coupon = couponRepository.findById(payProductRequest.getCouponId())
                .orElseThrow();

        Order orders = member.getOrders().stream()
                .filter(order -> order.getId().equals(payProductRequest.getOrderId()))
                .findAny().orElseThrow();

        // 특정 상품
        List<Product> productList = orders.getOrderItems().stream()
                .map(OrderItem::getProduct)
                .filter(Product::isCouponUseStatus)
                .collect(Collectors.toList());

        List<ProductOrderResponse> productOrderResponses = orders.getOrderItems().stream()
                .map(ProductOrderResponse::createProductOrderResponse)
                .collect(Collectors.toList());

        orders.setOrderStatus(OrderStatus.COMPLETE);

        //todo : 상품 감소 로직
        // 관심사에 따른 분리가 필요 , 지금 엄청 코드가 더러움

        /**
         * orderCalculatorWithCoupon 계산 로직을 개별 Component로 분리한 이유
         * - 처음에 PayCouponInfoResponse Dto에서 값을 처리를 했다.
         * - 하지만 비즈니스 로직을 Dto에서 처리하기 보다는 Service Layer에 위치를 하는게 좋다고 생각을 했다.
         * - 해당 로직은 프로젝트의 기능이 추가되면 자주 사용되는 기능 -> 계산 로직 (개별 컴포넌트 관리)
         *
         * - 해당 Data를 별도로 나눈 이유는 PayCouponInfoResponse에 Inline을 하게 된다면 가독성을 저하.
         */
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
