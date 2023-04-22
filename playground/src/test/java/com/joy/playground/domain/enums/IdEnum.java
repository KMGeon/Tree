package com.joy.playground.domain.enums;

public enum IdEnum {

    Default(1L),
    delete(99L);

    private final Long id;


    IdEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
