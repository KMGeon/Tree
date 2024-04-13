package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.domain.entity.order.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    ProductTimeResponse getProductInfoWhenTargetDate(Long productId, LocalDateTime targetDateTime);
    void createProduct(ProductCreateRequest productCreateRequest);
    void updateProduct(ProductUpdateRequest productUpdateRequest, Long productId, LocalDateTime now);
    void deleteProduct(Long productId);
    void declineProductQuantity(List<OrderItem> orderItems);
}
