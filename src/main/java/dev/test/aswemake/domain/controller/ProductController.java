package dev.test.aswemake.domain.controller;

import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.domain.service.ProductService;
import dev.test.aswemake.global.argument.IfLogin;
import dev.test.aswemake.global.argument.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public void createProduct(@RequestBody ProductCreateRequest request,
                              @IfLogin LoginUserDto loginUserDto) {
        log.info("product Name:{}, Price:{} / memberId : {}"
                , request.getName(), request.getPrice(), loginUserDto.getMemberId());
        productService.createProduct(request, loginUserDto);
    }

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_MARKET')")
    public void updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest,
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
        return productService.getProductInfoWhenTargetDate(productId, Optional.ofNullable(timestamp).orElse(LocalDateTime.now()));
    }

}
