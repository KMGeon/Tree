package dev.test.aswemake.domain.controller.dto.response.order;

import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
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
public class OrderPayByIdInfoResponse {

    private List<ProductOrderResponse> productOrderResponses = new ArrayList<>();
    private int totalCost;
    private String couponStatus;


    public static OrderPayByIdInfoResponse of(List<ProductOrderResponse> productOrderResponses, List<OrderItem> orderItems, String couponStatus, int deliveryFee) {
        return OrderPayByIdInfoResponse.builder()
                .productOrderResponses(productOrderResponses)
                .totalCost(calculateOrderItemCost(orderItems)+deliveryFee)
                .couponStatus(couponStatus)
                .build();
    }


    private static int calculateOrderItemCost(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(orderItem -> orderItem.getOrderPrice() * orderItem.getProductCount())
                .sum();
    }

}