package com.example.coupon.domain.coupon.dto.request;

import com.example.coupon.domain.coupon.domain.Coupon;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
  @NotNull(message = "할인금 입력하세요.")
  private Integer amount;

  @NotBlank(message = "쿠폰 이름 입력하세요.")
  private String name;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  public Coupon toEntity(){
    return Coupon.builder()
        .name(name)
        .amount(amount)
        .startDate(startDate)
        .endDate(endDate)
        .build();
  }
}
