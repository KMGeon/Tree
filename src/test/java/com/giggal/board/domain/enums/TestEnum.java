package com.giggal.board.domain.enums;


public enum TestEnum {

    VALID_EMAIL("validEmail@email.com"),
    VALID_PASSWORD("test123!"),
    DUPLICATE_EMAIL("duplicate@email.com"),
    INVALID_EMAIL("Invalid"),
    INVALID_PASSWORD("test123"),
    NAME("김무건");


    private final String name;


    TestEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
