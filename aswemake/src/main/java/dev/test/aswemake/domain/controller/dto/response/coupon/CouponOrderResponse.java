package dev.test.aswemake.domain.controller.dto.response.coupon;

import dev.test.aswemake.domain.entity.coupon.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponOrderResponse {
    private Long couponId;
    private String couponName;
    private int couponSalePrice;

    public static CouponOrderResponse of(Coupon coupon) {
        return CouponOrderResponse.builder()
                .couponId(coupon.getId())
                .couponName(coupon.getCouponName())
                .couponSalePrice(coupon.getSalePrice())
                .build();
    }

    public CouponOrderResponse(Coupon coupon) {
        this.couponId = coupon.getId();
        this.couponName = coupon.getCouponName();
        this.couponSalePrice = coupon.getSalePrice();
    }
}
