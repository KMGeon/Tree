package dev.test.aswemake.domain.controller.dto.response.order;

import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.enums.OrderStatus;
import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.Product;
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
    private ProductStrategy productStrategy;

    public static OrderPayInfoResponse of(List<List<OrderItem>> orderItemList, List<Order> orders) {
        return OrderPayInfoResponse.builder()
                .productOrderResponses(orders.stream()
                        .filter(order -> order.getOrderStatus() != OrderStatus.COMPLETE)
                        .collect(Collectors.toList()).stream()
                        .flatMap(order -> order.getOrderItems().stream())
                        .map(ProductOrderResponse::createProductOrderResponse)
                        .collect(Collectors.toList()))
                .totalCost((int) (calculateOrderItemListCost(orderItemList) + (5000* orders.stream()
                        .map(Order::getOrderItems)
                        .count())))
                .productStrategy(getSelectedStrategy(orders.stream()
                        .filter(order1 -> order1.getOrderStatus() != OrderStatus.COMPLETE)
                        .collect(Collectors.toList()).stream()
                        .flatMap(order1 -> order1.getOrderItems().stream())
                        .map(OrderItem::getProduct)
                        .collect(Collectors.toList()).stream()
                        .map(Product::getProductStrategy)
                        .collect(Collectors.toList())))
                .build();
    }

    private static int calculateOrderItemListCost(List<List<OrderItem>> memberOrders) {
        return memberOrders.stream()
                .flatMap(List::stream)
                .mapToInt(orderItem -> orderItem.getOrderPrice() * orderItem.getProductCount())
                .sum();
    }

    private static ProductStrategy getSelectedStrategy(List<ProductStrategy> productStrategy) {
        if (productStrategy.stream().anyMatch(strategy -> "SPECIFIC".equalsIgnoreCase(strategy.getStrategy()))) {
            return ProductStrategy.SPECIFIC;
        } else {
            return ProductStrategy.TOTAL;
        }
    }

}