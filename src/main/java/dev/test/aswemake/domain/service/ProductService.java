package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.global.argument.LoginUserDto;

import java.time.LocalDateTime;

public interface ProductService {
    ProductTimeResponse getProductInfoWhenTargetDate(Long productId, LocalDateTime targetDateTime);
    void createProduct(ProductCreateRequest productCreateRequest, LoginUserDto loginUserDto);
    void updateProduct(ProductUpdateRequest productUpdateRequest, Long productId, LocalDateTime now);
    void deleteProduct(Long productId);
}
