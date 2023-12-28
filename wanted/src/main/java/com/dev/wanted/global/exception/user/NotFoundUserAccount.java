package com.dev.wanted.global.exception.user;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class NotFoundUserAccount extends UserAbstractException {

    public NotFoundUserAccount(String message) {
        super("Not Found User Account : " + message);
    }

    public NotFoundUserAccount(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundUserAccount.getErrorCode();
    }
}
