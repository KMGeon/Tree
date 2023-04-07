package com.example.coupon.domain.coupon.dto.response;

import com.example.coupon.domain.coupon.domain.Coupon;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponse {

    private int amount;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @QueryProjection
    public CouponResponse(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public static CouponResponse convertCouponData(Coupon coupon) {
        return CouponResponse.builder()
                .name(coupon.getName())
                .amount(coupon.getAmount())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .build();
    }


}
