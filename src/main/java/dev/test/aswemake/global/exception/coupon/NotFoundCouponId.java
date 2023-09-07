package dev.test.aswemake.global.exception.coupon;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;

public class NotFoundCouponId extends CouponAbstractException {
    public NotFoundCouponId(Long message) {
        super("쿠폰의 아이디를 찾을 수 없습니다. "+message);
    }

    public NotFoundCouponId(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundCouponId.getErrorCode();
    }
}
