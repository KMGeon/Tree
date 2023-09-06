package dev.test.aswemake.domain.controller.dto.response.order;

import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPayInfoResponse {

    private List<ProductOrderResponse> productOrderResponses = new ArrayList<>();
    private int totalCost;
    private String couponStatus;

    public static OrderPayInfoResponse of(List<ProductOrderResponse> productOrderResponses, List<List<OrderItem>> memberOrders, String couponStatus, List<Order> orders) {
        return OrderPayInfoResponse.builder()
                .productOrderResponses(productOrderResponses)
                .totalCost(calculateOrderItemListCost(memberOrders) + (orders.stream()
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