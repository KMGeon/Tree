package dev.test.aswemake.domain.controller.dto.response.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponseDto {

    private String couponStatus;

    public static CouponResponseDto of(String couponStatus) {
        return CouponResponseDto.builder()
                .couponStatus(couponStatus)
                .build();
    }
}
