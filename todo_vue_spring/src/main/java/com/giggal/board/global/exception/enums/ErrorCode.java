package com.giggal.board.global.exception.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    BAD_REQUEST("400");

    private final String errorCode;


    ErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
