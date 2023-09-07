package dev.test.aswemake.global.exception.order;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;
import dev.test.aswemake.global.exception.coupon.CouponAbstractException;

public class NotFoundOrderId extends CouponAbstractException {
    public NotFoundOrderId(Long message) {
        super("주문 아이디를 찾을 수 없습니다. "+message);
    }

    public NotFoundOrderId(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundMemberId.getErrorCode();
    }
}
