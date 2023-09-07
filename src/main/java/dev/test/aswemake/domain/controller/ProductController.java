package dev.test.aswemake.domain.controller;

import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_MARKET')")
    public void createProduct(@RequestBody @Valid ProductCreateRequest request) {
        log.info("product Name:{}, Price:{}", request.getName(), request.getPrice());
        productService.createProduct(request);
    }

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_MARKET')")
    public void updateProduct(@RequestBody @Valid ProductUpdateRequest productUpdateRequest,
                              @PathVariable Long productId) {
        LocalDateTime now = LocalDateTime.now();
        productService.updateProduct(productUpdateRequest, productId, now);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_MARKET')")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }


    @GetMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MARKET')")
    public ProductTimeResponse getProductPriceAtTimestamp(@PathVariable Long productId,
                                                          @RequestParam(name = "timestamp", required = false)
                                                          @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp) {
        timestamp = Optional.ofNullable(timestamp).orElse(LocalDateTime.now());
        return productService.getProductInfoWhenTargetDate(productId, timestamp);
    }


}
