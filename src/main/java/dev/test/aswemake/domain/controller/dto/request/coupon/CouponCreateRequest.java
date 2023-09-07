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

    @NotBlank(message = "쿠폰 이름을 입력하세요.")
    private String couponName;

    @Positive(message = "쿠폰 할인가를 입력하세요.")
    private int salePrice;

    @NotNull(message = "RATE, FIX를 선택하세요.")
    private CouponSaleStrategy couponSaleStrategy;
}
