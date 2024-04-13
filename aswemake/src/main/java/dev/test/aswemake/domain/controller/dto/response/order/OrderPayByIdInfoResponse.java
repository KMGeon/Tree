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
public class OrderPayByIdInfoResponse {

    private List<ProductOrderResponse> productOrderResponses = new ArrayList<>();
    private int totalCost;
    private ProductStrategy productStrategy;

    public static OrderPayByIdInfoResponse of(Order order) {

        if (order.getOrderStatus() == OrderStatus.COMPLETE) {
            return OrderPayByIdInfoResponse.builder()
                    .productOrderResponses(new ArrayList<>())
                    .totalCost(0)
                    .productStrategy(null)
                    .build();
        }

        return OrderPayByIdInfoResponse.builder()
                .productOrderResponses(order.getOrderItems().stream()
                        .map(ProductOrderResponse::createProductOrderResponse)
                        .collect(Collectors.toList()))
                .totalCost(calculateOrderItemCost(order.getOrderItems())+5000)
                .productStrategy(getSelectedStrategy(order.getOrderItems().stream()
                        .map(OrderItem::getProduct)
                        .collect(Collectors.toList()).stream()
                        .map(Product::getProductStrategy)
                        .collect(Collectors.toList())))
                .build();
    }



    private static int calculateOrderItemCost(List<OrderItem> orderItems) {
        return orderItems.stream()
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