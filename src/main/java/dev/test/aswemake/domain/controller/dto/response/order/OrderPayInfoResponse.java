package dev.test.aswemake.domain.controller.dto.response.order;

import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.enums.OrderStatus;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPayInfoResponse {

    private List<ProductOrderResponse> productOrderResponses = new ArrayList<>();
    private int totalCost;
    private String couponStatus;

    public static OrderPayInfoResponse of(List<List<OrderItem>> memberOrders, String couponStatus, List<Order> orders) {
        return OrderPayInfoResponse.builder()
                .productOrderResponses(orders.stream()
                        .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                        .collect(Collectors.toList()).stream()
                        .flatMap(order -> order.getOrderItems().stream())
                        .map(ProductOrderResponse::createProductOrderResponse)
                        .collect(Collectors.toList()))
                .totalCost(calculateOrderItemListCost(memberOrders) + (orders.stream()
                        .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                        .mapToInt(Order::getDeliveryFee)
                        .sum()))
                .couponStatus(couponStatus)
                .build();
    }

    private static int calculateOrderItemListCost(List<List<OrderItem>> memberOrders) {
        return memberOrders.stream()
                .flatMap(List::stream)
                .mapToInt(orderItem -> orderItem.getOrderPrice() * orderItem.getProductCount())
                .sum();
    }

}