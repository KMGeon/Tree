package dev.test.aswemake.global.exception.member;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;

public class NotMatchMemberPassword extends MemberAbstractException {


    public NotMatchMemberPassword(String message) {
        super("비밀번호가 일치하지 않습니다."+message);
    }

    public NotMatchMemberPassword(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotMatchMemberPassword.getErrorCode();
    }
}
