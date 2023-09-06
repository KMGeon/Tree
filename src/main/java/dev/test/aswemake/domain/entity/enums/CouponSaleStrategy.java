package dev.test.aswemake.domain.entity.enums;

import lombok.Getter;

@Getter
public enum CouponSaleStrategy {

    RATE("RATE"),
    FIX("FIX");

    private final String strategy;


    CouponSaleStrategy(String strategy) {
        this.strategy = strategy;
    }
}
