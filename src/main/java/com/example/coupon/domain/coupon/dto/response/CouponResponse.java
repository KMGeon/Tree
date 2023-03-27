package com.example.coupon.domain.coupon.dto.response;

import com.example.coupon.domain.coupon.domain.Coupon;
import java.time.LocalDateTime;
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

  public static CouponResponse convertCouponData(Coupon coupon) {
    return CouponResponse.builder()
        .name(coupon.getName())
        .amount(coupon.getAmount())
        .startDate(coupon.getStartDate())
        .endDate(coupon.getEndDate())
        .build();
  }



}
