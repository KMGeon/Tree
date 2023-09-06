package dev.test.aswemake.domain.controller.dto.response.product;

import dev.test.aswemake.domain.entity.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderResponse {
    private Long productId;
    private String productName;
    private int productPrice;
    private int orderProductQuantity;

    public static ProductOrderResponse createProductOrderResponse(OrderItem orderItem) {
        return ProductOrderResponse.builder()
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProduct().getName())
                .productPrice(orderItem.getProduct().getPrice())
                .orderProductQuantity(orderItem.getProductCount())
                .build();
    }
}