package dev.test.aswemake.domain.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayProductRequest {
    @NotNull(message = "쿠폰 아이디를 입력하세요")
    private Long couponId;
    @NotNull(message = "주문 아이디를 입력하세요")
    @Positive(message = "주문 아이디는 양수로 입력을 하세요")
    private Long orderId;
}
