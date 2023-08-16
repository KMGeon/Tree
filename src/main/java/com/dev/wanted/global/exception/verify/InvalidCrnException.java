package com.dev.wanted.global.exception.verify;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class InvalidCrnException extends VerifyAbstractException {

    public InvalidCrnException(String message) {
        super("Invalid crn code : " + message);
    }

    public InvalidCrnException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundVerify.getErrorCode();
    }
}
