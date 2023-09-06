package dev.test.aswemake.domain.controller.dto.request.coupon;

import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponCreateRequest {

    @NotBlank(message = "Coupon name cannot be blank")
    private String couponName;

    @Positive(message = "Sale price must be a positive integer")
    private int salePrice;

    @NotNull(message = "Coupon sale strategy must be specified")
    private CouponSaleStrategy couponSaleStrategy;
}
