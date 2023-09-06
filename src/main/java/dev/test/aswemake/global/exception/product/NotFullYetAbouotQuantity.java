package dev.test.aswemake.global.exception.product;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;
import dev.test.aswemake.global.exception.member.MemberAbstractException;

public class NotFullYetAbouotQuantity extends MemberAbstractException {


    public NotFullYetAbouotQuantity(int message) {
        super("수량이 충분하지 않습니다."+message);
    }

    public NotFullYetAbouotQuantity(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotMatchMemberPassword.getErrorCode();
    }
}
