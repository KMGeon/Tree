package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayByIdInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayInfoResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 주문 생성하기
     * - 전제 조건 :  product가 있어야 한다.
     * - OrderItem.createOrderItem()-> orderItem을 생성한다.
     * - Order.createOrder()->ORDER을 생성한다.
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
     * - 조건
     * 1. 주문을 조회하고 order_item을 조회하고 product의 상태가 특정 할인이 가능한 상품이 있으면 SPECIFIC
     * 2. 주문을 조회하고 order_item을 조회하고 product의 상태가 특정 할인이 가능한 상품이 없다면 TOTAL
     * 3. Order의 상태를 검증하고 WAITING일 경우에 조회한다.
     *
     *  fetch join을 통해서 가져오고 이후로 batch size를 통해서 조회
     *  관련 매핑은 DTO에서 처리
     */
    @Override
    @Transactional(readOnly = true)
    public OrderPayByIdInfoResponse getOrderInfoByOrderId(LoginUserDto loginUserDto, Long orderId) {
        Order order = orderRepository.findOrderWithItemsAndProducts(orderId)
                .orElseThrow(() -> new NotFoundOrderId(orderId));

        return OrderPayByIdInfoResponse.of(order);
    }

    /**
     * 회원의 전체 주문을 조회를 한다.
     * member (1) : order (N)이고 order(1) : orderItem(N) 이다.
     * 이걸 2개를 fetch join을 하면 컬렉션 여러개를 가져오면 MultipleBagFetchException 발생
     * fetch join을 통해서 가져오고 이후로 batch size를 통해서 조회
     * 관련 매핑은 DTO에서 처리
     */
    @Override
    @Transactional(readOnly = true)
    public OrderPayInfoResponse getOrderInfo(LoginUserDto loginUserDto) {

        Member member = memberRepository.findByIdWithOrder(loginUserDto.getMemberId())
                .orElseThrow(() -> new NotFoundMemberId(loginUserDto.getMemberId()));


        List<List<OrderItem>> orderItemList = member.getOrders().stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                .collect(Collectors.toList()).stream()
                .map(Order::getOrderItems)
                .collect(Collectors.toList());

        return OrderPayInfoResponse.of(orderItemList, member.getOrders());

    }

}