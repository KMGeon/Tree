package dev.test.aswemake.global.exception.member;

import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;

public class NotFoundMemberId extends MemberAbstractException {
    public NotFoundMemberId(Long message) {
        super("회원의 아이디를 찾을 수 없습니다. "+message);
    }

    public NotFoundMemberId(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundMemberId.getErrorCode();
    }
}
