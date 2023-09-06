package dev.test.aswemake.global.exception.StatusEnum;

import lombok.Getter;

@Getter
public enum ErrorCustomEnum {

    NotFoundMemberId(1000),
    NotMatchMemberPassword(1001);

    private final int errorCode;

    ErrorCustomEnum(int errorCode) {
        this.errorCode = errorCode;
    }


}
