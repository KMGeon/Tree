package com.dev.wanted.global.exception.verify;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class DuplicateCrnException extends VerifyAbstractException {

    public DuplicateCrnException(String message) {
        super("Duplicate Crn Code : " + message);
    }

    public DuplicateCrnException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.DuplicateCrnException.getErrorCode();
    }
}
