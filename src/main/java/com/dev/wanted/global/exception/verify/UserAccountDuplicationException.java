package com.dev.wanted.global.exception.verify;


import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class UserAccountDuplicationException extends VerifyAbstractException {

    public UserAccountDuplicationException(String message) {
        super("User Account is already exist" + message);
    }

    public UserAccountDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.UserAccountDuplicationException.getErrorCode();
    }
}
