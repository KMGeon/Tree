package dev.test.aswemake.global.exception.product;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;
import dev.test.aswemake.global.exception.member.MemberAbstractException;

public class NotFoundProductId extends MemberAbstractException {


    public NotFoundProductId(Long message) {
        super("상품의 ID를 찾을 수 없습니다. : "+message);
    }

    public NotFoundProductId(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundProductId.getErrorCode();
    }
}
