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
import dev.test.aswemake.domain.repository.OrderItemRepository;
import dev.test.aswemake.domain.repository.OrderRepository;
import dev.test.aswemake.domain.repository.ProductRepository;
import dev.test.aswemake.domain.service.OrderService;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.member.NotFoundMemberId;
import dev.test.aswemake.global.exception.product.NotFoundProductId;
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
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            MemberRepository memberRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * 주문 전체 (배달비 제외)
     * 특정 상품 한정 (특정 상품의 모든 개수에 적용)
     */
    public static final String TOTAL = "쿠폰을 사용하면 전체 할인이 가능합니다.";
    public static final String SPECIFIC = "특정 상품 한정 할인이 가능한 상품이 있어 특정 상품 할인 쿠폰만 사용이 가능합니다.";


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
        orderItemRepository.saveAll(orderItems);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderPayByIdInfoResponse getOrderInfoByOrderId(LoginUserDto loginUserDto, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow();

        if (order.getOrderStatus() == OrderStatus.COMPLETE) {
            return OrderPayByIdInfoResponse.of(new ArrayList<>(), new ArrayList<>(), null, order.getDeliveryFee());
        }

        List<ProductOrderResponse> productOrderResponses = order.getOrderItems().stream()
                .map(ProductOrderResponse::createProductOrderResponse)
                .collect(Collectors.toList());

        List<Product> productList = order.getOrderItems().stream()
                .map(OrderItem::getProduct)
                .collect(Collectors.toList());

        String couponStatus = productList.stream().anyMatch(Product::isCouponUseStatus) ? TOTAL : SPECIFIC;

        return OrderPayByIdInfoResponse.of(productOrderResponses, order.getOrderItems(), couponStatus, order.getDeliveryFee());

    }

    @Override
    @Transactional(readOnly = true)
    public OrderPayInfoResponse getOrderInfo(LoginUserDto loginUserDto) {
        Member member = memberRepository.findById(loginUserDto.getMemberId())
                .orElseThrow(() -> new NotFoundMemberId(loginUserDto.getMemberId()));

        List<Order> orders = member.getOrders();


        List<List<OrderItem>> orderItemList = orders.stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                .collect(Collectors.toList()).stream()
                .map(Order::getOrderItems)
                .collect(Collectors.toList());

        List<ProductOrderResponse> productOrderResponses = orders.stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                .collect(Collectors.toList()).stream()
                .flatMap(order -> order.getOrderItems().stream())
                .map(ProductOrderResponse::createProductOrderResponse)
                .collect(Collectors.toList());

        List<Product> productList = orders.stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                .collect(Collectors.toList()).stream()
                .flatMap(order -> order.getOrderItems().stream())
                .map(OrderItem::getProduct)
                .collect(Collectors.toList());

        String couponStatus = productList.stream().anyMatch(Product::isCouponUseStatus) ? TOTAL : SPECIFIC;

        return OrderPayInfoResponse.of(productOrderResponses, orderItemList, couponStatus, orders);
    }

}