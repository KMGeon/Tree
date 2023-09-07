package dev.test.aswemake.global.exception.member;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;

public class NotFoundMemberEmail extends MemberAbstractException {
    public NotFoundMemberEmail(String message) {
        super("회원의 아이디를 찾을 수 없습니다. "+message);
    }

    public NotFoundMemberEmail(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundMemberEmail.getErrorCode();
    }
}
