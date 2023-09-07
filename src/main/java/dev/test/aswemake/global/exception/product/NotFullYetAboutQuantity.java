package dev.test.aswemake.global.exception.product;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;
import dev.test.aswemake.global.exception.member.MemberAbstractException;

public class NotFullYetAboutQuantity extends MemberAbstractException {


    public NotFullYetAboutQuantity(int message) {
        super("수량이 충분하지 않습니다."+message);
    }

    public NotFullYetAboutQuantity(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFullYetAboutQuantity.getErrorCode();
    }
}
