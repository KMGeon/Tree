package com.dev.wanted.global.exception.user;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class NotFoundUserId extends UserAbstractException {

    public NotFoundUserId(Long message) {
        super("Not Found User Id : " + message);
    }

    public NotFoundUserId(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundUserId.getErrorCode();
    }
}
