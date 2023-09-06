package dev.test.aswemake.domain.controller.dto.response.order;

import dev.test.aswemake.domain.controller.dto.response.coupon.CouponOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private List<CouponOrderResponse> couponOrderResponses;
    private int originalTotalCost;
    private int discountedTotalCost;
}
