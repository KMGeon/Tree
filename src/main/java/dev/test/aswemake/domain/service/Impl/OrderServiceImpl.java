package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayByIdInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.enums.OrderStatus;
import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.repository.OrderRepository;
import dev.test.aswemake.domain.repository.ProductRepository;
import dev.test.aswemake.domain.service.OrderItemService;
import dev.test.aswemake.domain.service.OrderService;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.member.NotFoundMemberId;
import dev.test.aswemake.global.exception.order.NotFoundOrderId;
import dev.test.aswemake.global.exception.product.NotFoundProductId;
import dev.test.aswemake.global.exception.product.NotFullYetAbouotQuantity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderItemService orderItemService;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            MemberRepository memberRepository,
            ProductRepository productRepository,
            OrderItemService orderItemService
    ) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.orderItemService = orderItemService;
    }

    /**
     * 주문 전체 (배달비 제외)
     * 특정 상품 한정 (특정 상품의 모든 개수에 적용)
     */
    public static final String TOTAL = "쿠폰을 사용하면 전체 할인이 가능합니다.";
    public static final String SPECIFIC = "특정 상품 한정 할인이 가능한 상품이 있어 특정 상품 할인 쿠폰만 사용이 가능합니다.";
    public static final String NO_CONTENT = "";


    /**
     * 주문 생성하기
     * - 전제 조건 :  product가 있어야 한다.
     * - OrderItem.createOrderItem을 통하여 orderItem을 생성한다.
     * - Order.createOrder을 통하여 주문을 생성한다.
     * <p>
     * - 전체적인 시나리오 : 상품 1,2이 있다고 가정하면 주문에서 상품 1을 1개, 상품 2를 10개를 주문한다.
     * - 상품의 개수를 판단하고 만약에 상품의 개수보다 많은 수를 주문하면 exception
     * - 만약에 올바른 조건이면 하나의 order를 생성하고 여러개의 주문 상품이 있는 orderItems를 만든다.
     *
     * @param loginUserDto       회원 정보
     * @param orderCreateRequest 상품의 아이디, 상품의 수량
     * @throws NotFullYetAbouotQuantity 수량이 부족하다.
     */
    @Override
    @Transactional
    public void createOrder(LoginUserDto loginUserDto, OrderCreateRequest orderCreateRequest) {
        Member member = memberRepository.findById(loginUserDto.getMemberId())
                .orElseThrow(() -> new NotFoundMemberId(loginUserDto.getMemberId()));

        List<OrderItem> orderItems = orderCreateRequest.getOrderCreateListRequests().stream()
                .map(createRequest -> {
                    Product product = productRepository.findById(createRequest.getItemId())
                            .orElseThrow(() -> new NotFoundProductId(createRequest.getItemId()));

                    return OrderItem.createOrderItem(product, product.getPrice(), createRequest.getCount());
                }).collect(Collectors.toList());

        orderRepository.save(Order.createOrder(member, orderItems));
        orderItemService.orderItemsSave(orderItems);
    }

    /**
     * 단일 주문 조회하기
     * <p>
     * - 조건
     * 1. 주문을 조회하고 order_item을 조회하고 product의 상태가 특정 할인이 가능한 상품이 있으면
     * SPECIFIC(특정 상품 한정 할인이 가능한 상품이 있어 특정 상품 할인 쿠폰만 사용이 가능합니다.)
     * <p>
     * 2. 주문을 조회하고 order_item을 조회하고 product의 상태가 특정 할인이 가능한 상품이 있으면 TOTAL(쿠폰을 사용하면 전체 할인이 가능합니다.)
     * <p>
     * 3. Order의 상태를 검증하고 WAITING일 경우에 조회한다.
     * <p>
     * 4. 만약에 상태가 Complete이면 다음과 같이 반환한다.
     *
     * @param loginUserDto 로그인 회원 정보
     * @param orderId      주문 단일 아이디
     * @return OrderPayByIdInfoResponse (productOrderResponses [List] , totalCost (int) , couponStatus (String)
     * @throws NotFoundOrderId
     */

    @Override
    @Transactional(readOnly = true)
    public OrderPayByIdInfoResponse getOrderInfoByOrderId(LoginUserDto loginUserDto, Long orderId) {

        //order (1) - orderItem (N) - product (1) : N:M 관계
        // N+1 문제 해결을 위해 fetch join
        Order order = orderRepository.findOrderWithItemsAndProducts(orderId)
                .orElseThrow(() -> new NotFoundOrderId(orderId));

        if (order.getOrderStatus() == OrderStatus.COMPLETE) {
            return OrderPayByIdInfoResponse.of(new ArrayList<>(), new ArrayList<>(), null, 0);
        }

        List<ProductOrderResponse> productOrderResponses = order.getOrderItems().stream()
                .map(ProductOrderResponse::createProductOrderResponse)
                .collect(Collectors.toList());

        String couponStatus = order.getOrderItems().stream()
                .anyMatch(orderItem -> orderItem.getProduct().isCouponUseStatus()) ? TOTAL : SPECIFIC;

        return OrderPayByIdInfoResponse.of(productOrderResponses, order.getOrderItems(), couponStatus, order.getDeliveryFee());

    }

    /**
     * 전체 주문을 조회한다.
     * - 회원이 가지고 있는 주문을 전체 조회한다.
     * - getOrderInfoByOrderId의 조건과 동일하게 적용한다.
     *
     * @param loginUserDto 로그인 사용자 정보
     * @return OrderPayInfoResponse
     * @throws NotFoundMemberId
     */
    @Override
    @Transactional(readOnly = true)
    public OrderPayInfoResponse getOrderInfo(LoginUserDto loginUserDto) {

        /**
         * member (1) : order (N)이고 order(1) : orderItem(N) 이다.
         * 이걸 2개를 fetch join을 하면 컬렉션 여러개를 가져오면 MultipleBagFetchException 발생
         * fetch join을 통해서 가져오고 이후로 batch size를 통해서 조회
         * 총 쿼리는 fetch join (1) batch size(2)
         */
        Member member = memberRepository.findByIdWithOrder(loginUserDto.getMemberId())
                .orElseThrow(() -> new NotFoundMemberId(loginUserDto.getMemberId()));


        List<List<OrderItem>> orderItemList = member.getOrders().stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                .collect(Collectors.toList()).stream()
                .map(Order::getOrderItems)
                .collect(Collectors.toList());

        List<Product> productList = member.getOrders().stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                .collect(Collectors.toList()).stream()
                .flatMap(order -> order.getOrderItems().stream())
                .map(OrderItem::getProduct)
                .collect(Collectors.toList());

        String couponStatus = productList.stream().anyMatch(Product::isCouponUseStatus) ? TOTAL : SPECIFIC;

        List<Order> collect = member.getOrders().stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                .collect(Collectors.toList());

        couponStatus = collect.isEmpty() ? NO_CONTENT : couponStatus;

        return OrderPayInfoResponse.of(orderItemList, couponStatus, member.getOrders());
    }

}