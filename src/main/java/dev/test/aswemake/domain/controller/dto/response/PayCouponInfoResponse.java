package dev.test.aswemake.domain.controller.dto.response;

import dev.test.aswemake.domain.controller.dto.response.coupon.CouponOrderResponse;
import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.coupon.Coupon;
import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayCouponInfoResponse {

    private List<ProductOrderResponse> productOrderResponses = new ArrayList<>();
    private int totalCost;
    private CouponOrderResponse couponOrderResponse;

    public static PayCouponInfoResponse TOTAL(Order order, Coupon coupon) {
        return PayCouponInfoResponse.builder()
                .productOrderResponses(order.getOrderItems().stream()
                        .map(ProductOrderResponse::createProductOrderResponse)
                        .collect(Collectors.toList()))
                .totalCost(getTotalCost(order, coupon) + order.getDeliveryFee())
                .couponOrderResponse(CouponOrderResponse.builder()
                        .couponId(coupon.getId())
                        .couponName(coupon.getCouponName())
                        .couponSalePrice(coupon.getSalePrice())
                        .build())
                .build();
    }


    public static PayCouponInfoResponse SPECIFIC(Order order, Coupon coupon) {
        return PayCouponInfoResponse.builder()
                .productOrderResponses(order.getOrderItems().stream()
                        .map(ProductOrderResponse::createProductOrderResponse)
                        .collect(Collectors.toList()))
                .totalCost(getSpecificTotalCost(order, coupon) + order.getDeliveryFee())
                .couponOrderResponse(CouponOrderResponse.builder()
                        .couponId(coupon.getId())
                        .couponName(coupon.getCouponName())
                        .couponSalePrice(coupon.getSalePrice())
                        .build())
                .build();
    }

    private static int getSpecificTotalCost(Order order, Coupon coupon) {
        int totalCost = 0;

        if (coupon.getCouponSaleStrategy() == CouponSaleStrategy.FIX) {
            List<OrderItem> orderItems = order.getOrderItems();

            int totalSUM = orderItems.stream()
                    .filter(orderItem -> {
                        Product product = orderItem.getProduct();
                        return product.getProductStrategy() == ProductStrategy.TOTAL;
                    })
                    .mapToInt(orderItem -> orderItem.getOrderPrice() * orderItem.getProductCount())
                    .sum();

            int specificSUM = orderItems.stream()
                    .filter(orderItem -> {
                        Product product = orderItem.getProduct();
                        return product.getProductStrategy() == ProductStrategy.SPECIFIC;
                    })
                    .mapToInt(orderItem -> (orderItem.getOrderPrice() - coupon.getSalePrice()) * orderItem.getProductCount())
                    .sum();

            totalCost = totalSUM + specificSUM;

        }


        if (coupon.getCouponSaleStrategy() == CouponSaleStrategy.RATE) {
            List<OrderItem> orderItems = order.getOrderItems();
            List<OrderItem> specificOrderItems = orderItems.stream()
                    .filter(orderItem -> orderItem.getProduct().getProductStrategy() == ProductStrategy.SPECIFIC)
                    .collect(Collectors.toList());

            List<OrderItem> totalOrderItems = orderItems.stream()
                    .filter(orderItem -> orderItem.getProduct().getProductStrategy() == ProductStrategy.TOTAL)
                    .collect(Collectors.toList());

            int totalSum = totalOrderItems.stream()
                    .mapToInt(orderItem -> orderItem.getOrderPrice() * orderItem.getProductCount())
                    .sum();

            int specificSum = specificOrderItems.stream()
                    .mapToInt(orderItem -> {
                        int discountedPrice = (int) (orderItem.getOrderPrice() * (1 - (coupon.getSalePrice() / 100.0)));
                        log.info(" 할인가 금액 : {}", discountedPrice);
                        return discountedPrice * orderItem.getProductCount();
                    })
                    .sum();

            totalCost = totalSum + specificSum;
        }
        return totalCost;
    }

    private static int getTotalCost(Order order, Coupon coupon) {
        int totalCost = 0;

        if (coupon.getCouponSaleStrategy() == CouponSaleStrategy.FIX) {
            totalCost = order.getOrderItems().stream()
                    .mapToInt(orderItem -> {
                        int result = (orderItem.getOrderPrice() - coupon.getSalePrice()) * orderItem.getProductCount();
                        if (result <= 0) {
                            //todo : 하하
                            throw new RuntimeException("Invalid result: " + result);
                        }
                        return result;
                    })
                    .sum();
        }

        if (coupon.getCouponSaleStrategy() == CouponSaleStrategy.RATE) {
            totalCost = order.getOrderItems().stream()
                    .mapToInt(orderItem -> {
                        int discountedPrice = (int) (orderItem.getOrderPrice() * (1 - (coupon.getSalePrice() / 100.0)));
                        log.info("개별 할인가 금액 : {}", discountedPrice);
                        return discountedPrice * orderItem.getProductCount();
                    })
                    .sum();
        }
        return totalCost;
    }

}