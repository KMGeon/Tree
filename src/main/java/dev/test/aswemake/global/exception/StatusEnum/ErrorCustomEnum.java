package dev.test.aswemake.global.exception.StatusEnum;

import lombok.Getter;

@Getter
public enum ErrorCustomEnum {

    NotFoundMemberId(1000),
    NotMatchMemberPassword(1001),
    NotFoundMemberEmail(1002),
    NotFoundCouponId(2000),
    RateTooHighException(2001),
    NotFoundOrderId(3000),
    NotFoundProductId(4000),
    NotFullYetAboutQuantity(4001);


    private final int errorCode;

    ErrorCustomEnum(int errorCode) {
        this.errorCode = errorCode;
    }


}
