package com.giggal.board.domain.post.enums;

import lombok.Getter;

@Getter
public enum BadTitle {

    dog("나쁜말1"),
    ssibal("나쁜말2"),
    joy("나쁜말3"),
    reload("나쁜말4");

    private final String titleName;


    BadTitle(String titleName) {
        this.titleName = titleName;
    }

}
