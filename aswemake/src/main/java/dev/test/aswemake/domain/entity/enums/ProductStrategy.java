package dev.test.aswemake.domain.entity.enums;

import lombok.Getter;

@Getter
public enum ProductStrategy {

    TOTAL("TOTAL"),
    SPECIFIC("SPECIFIC");

    private final String strategy;


    ProductStrategy(String strategy) {
        this.strategy = strategy;
    }
}
