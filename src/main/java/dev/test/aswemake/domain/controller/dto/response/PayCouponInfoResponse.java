package dev.test.aswemake.domain.controller.dto.response;

import dev.test.aswemake.domain.controller.dto.response.coupon.CouponOrderResponse;
import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.coupon.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayCouponInfoResponse {

    private List<ProductOrderResponse> productOrderResponses = new ArrayList<>();
    private int totalCost;
    private CouponOrderResponse couponOrderResponse;


    public static PayCouponInfoResponse SPECIFIC(List<ProductOrderResponse> productOrderResponses, int totalCost, Coupon coupon) {
        return PayCouponInfoResponse.builder()
                .productOrderResponses(productOrderResponses)
                .totalCost(totalCost)
                .couponOrderResponse(CouponOrderResponse.builder()
                        .couponId(coupon.getId())
                        .couponName(coupon.getCouponName())
                        .couponSalePrice(coupon.getSalePrice())
                        .build())
                .build();
    }

    public static PayCouponInfoResponse TOTAL(List<ProductOrderResponse> productOrderResponses, int calculateTotalPrice, Coupon coupon) {
        return PayCouponInfoResponse.builder()
                .productOrderResponses(productOrderResponses)
                .totalCost(calculateTotalPrice)
                .couponOrderResponse(CouponOrderResponse.builder()
                        .couponId(coupon.getId())
                        .couponName(coupon.getCouponName())
                        .couponSalePrice(coupon.getSalePrice())
                        .build())
                .build();
    }

}