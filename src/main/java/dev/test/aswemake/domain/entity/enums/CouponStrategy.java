package dev.test.aswemake.domain.entity.enums;

import lombok.Getter;

@Getter
public enum CouponStrategy {

    TOTAL("TOTAL"),
    SPECIFIC("SPECIFIC");

    private final String strategy;


    CouponStrategy(String strategy) {
        this.strategy = strategy;
    }
}
