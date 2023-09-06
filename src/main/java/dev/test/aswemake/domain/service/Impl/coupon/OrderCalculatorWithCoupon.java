package dev.test.aswemake.domain.service.Impl.coupon;

import dev.test.aswemake.domain.entity.coupon.Coupon;
import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import dev.test.aswemake.domain.entity.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCalculatorWithCoupon {


    // 상품에서 특정 할인가가 가능한 상품이 없는 경우
    public int calculateTotalPrice(Order order, Coupon coupon) {

        int totalProductPrice = order.getOrderItems().stream()
                .mapToInt(value -> value.getOrderPrice() * value.getProductCount())
                .sum();

        // 쿠폰이 고정값
        if (coupon.getCouponSaleStrategy() == CouponSaleStrategy.FIX) {
            return totalProductPrice - coupon.getSalePrice();
        }
        // 쿠폰이 비율
        else {
            double discountRate = coupon.getSalePrice() / 100.0;

            log.info("discountRate:{}", discountRate);

            return (int) (totalProductPrice * (1 - discountRate));
        }
    }


    public int calculateSpecificTotalPrice(Order order, Coupon coupon) {
        //특정 상품
        int specificProductPrice = order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getProduct().isCouponUseStatus())
                .mapToInt(value -> value.getOrderPrice() * value.getProductCount())
                .sum();

        log.info("specificProductPrice : {}", specificProductPrice);

        //일반 상품
        int totalProductPrice = order.getOrderItems().stream()
                .filter(orderItem -> !orderItem.getProduct().isCouponUseStatus())
                .mapToInt(value -> value.getOrderPrice() * value.getProductCount())
                .sum();

        log.info("totalProductPrice: {}", totalProductPrice);

        // 특정 상품에 대한 할인 계산
        if (coupon.getCouponSaleStrategy() == CouponSaleStrategy.FIX) {
            int discountSum = specificProductPrice - coupon.getSalePrice();
            return totalProductPrice + discountSum + order.getDeliveryFee();
        }
        // 일반 상품에 대한 할인 계산
        else {
            double discountRate = coupon.getSalePrice() / 100.0;
            int sum1AfterDiscount = (int) (totalProductPrice * (1 - discountRate));
            return specificProductPrice + sum1AfterDiscount + order.getDeliveryFee();
        }
    }


}
