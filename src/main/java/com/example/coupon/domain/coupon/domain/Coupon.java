package com.example.coupon.domain.coupon.domain;

import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Coupon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private int amount;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  @Builder.Default
  private boolean useCoupon = false;

  public void destroy() {
    useCoupon = true;
  }

  public void updateCoupon(RequestDto requestDto){
    this.name = requestDto.getName();
    this.amount = requestDto.getAmount();
    this.startDate = requestDto.getStartDate();
    this.endDate = requestDto.getEndDate();
  }

  public CouponResponse convertCouponData(Coupon coupon) {
    return CouponResponse.builder()
        .name(coupon.getName())
        .amount(coupon.getAmount())
        .startDate(coupon.getStartDate())
        .endDate(coupon.getEndDate())
        .build();
  }
}
