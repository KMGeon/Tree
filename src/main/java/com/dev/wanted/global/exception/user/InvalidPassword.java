package com.dev.wanted.global.exception.user;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class InvalidPassword extends UserAbstractException {

    public InvalidPassword(String message) {
        super("InValid Match Password : " + message);
    }

    public InvalidPassword(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.InvalidPassword.getErrorCode();
    }
}
