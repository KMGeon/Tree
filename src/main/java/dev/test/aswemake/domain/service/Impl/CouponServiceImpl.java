package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.controller.dto.request.PayProductRequest;
import dev.test.aswemake.domain.controller.dto.request.coupon.CouponCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.PayCouponInfoResponse;
import dev.test.aswemake.domain.entity.coupon.Coupon;
import dev.test.aswemake.domain.entity.enums.OrderStatus;
import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.repository.CouponRepository;
import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.repository.OrderRepository;
import dev.test.aswemake.domain.service.CouponService;
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
    private final OrderRepository orderRepository;

    public CouponServiceImpl(
            CouponRepository couponRepository,
            MemberRepository memberRepository,
            ProductService productService,
            OrderRepository orderRepository
    ) {
        this.couponRepository = couponRepository;
        this.memberRepository = memberRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
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
     */
    @Override
    @Transactional
    public PayCouponInfoResponse processPaymentWithCoupon(LoginUserDto loginUserDto, PayProductRequest payProductRequest) {

        Member member = getMemberById(loginUserDto);

        Coupon coupon = getCouponByIdEqualsWithRequestId(payProductRequest, member);

        Order order = getOrderByIdEqualsRequestId(payProductRequest);

        // Bulk update를 하기 위해서 Modifying
        productService.declineProductQuantity(order.getOrderItems());

        order.setOrderStatus(OrderStatus.COMPLETE);

        couponRepository.deleteById(coupon.getId());

        if (getSpecificProductList(order).isEmpty()) {
            return PayCouponInfoResponse.TOTAL(order, coupon);
        } else {
            return PayCouponInfoResponse.SPECIFIC(order, coupon);
        }
    }

    private Order getOrderByIdEqualsRequestId(PayProductRequest payProductRequest) {
        Order order = orderRepository.findOrderWithItemsAndProducts(payProductRequest.getOrderId())
                .orElseThrow(() -> new NotFoundOrderId(payProductRequest.getCouponId()));
        return order;
    }

    private static Coupon getCouponByIdEqualsWithRequestId(PayProductRequest payProductRequest, Member member) {
        Coupon coupon = member.getCoupons().stream()
                .filter(coupon1 -> Objects.equals(coupon1.getId(), payProductRequest.getCouponId()))
                .findAny()
                .orElseThrow(() -> new NotFoundCouponId(payProductRequest.getCouponId()));
        return coupon;
    }

    private Member getMemberById(LoginUserDto loginUserDto) {
        Member member = memberRepository.findByIdWithCoupon(loginUserDto.getMemberId())
                .orElseThrow(() -> new NotFoundOrderId(loginUserDto.getMemberId()));
        return member;
    }

    private List<Product> getSpecificProductList(Order order) {
        return order.getOrderItems().stream()
                .map(OrderItem::getProduct)
                .filter(product -> product.getProductStrategy() == ProductStrategy.SPECIFIC)
                .collect(Collectors.toList());
    }

}
