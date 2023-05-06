package com.giggal.board.domain.post.enums;

import lombok.Getter;

@Getter
public enum BadTitle {

    dog("개새끼"),
    ssibal("씨발"),
    joy("놀이터"),
    reload("무료충전");

    private String roleName;


    BadTitle(String roleName) {
        this.roleName = roleName;
    }

}
