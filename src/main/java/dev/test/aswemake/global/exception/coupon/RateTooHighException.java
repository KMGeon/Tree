package dev.test.aswemake.global.exception.coupon;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;

public class RateTooHighException extends CouponAbstractException {
    public RateTooHighException(int message) {
        super("쿠폰의 할인 비율이 100%가 넘는다. "+message);
    }

    public RateTooHighException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundMemberId.getErrorCode();
    }
}
