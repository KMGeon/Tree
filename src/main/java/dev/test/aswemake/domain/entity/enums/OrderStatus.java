package dev.test.aswemake.domain.entity.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    WAITING("WAITING"),
    COMPLETE("COMPLETE");

    private final String strategy;


    OrderStatus(String strategy) {
        this.strategy = strategy;
    }
}
