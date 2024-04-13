package dev.test.aswemake.global.exception.product;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;
import dev.test.aswemake.global.exception.member.MemberAbstractException;

public class NotFoundProductName extends MemberAbstractException {


    public NotFoundProductName(String message) {
        super("상품의 이름를 찾을 수 없습니다. : "+message);
    }

    public NotFoundProductName(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundProductId.getErrorCode();
    }
}
