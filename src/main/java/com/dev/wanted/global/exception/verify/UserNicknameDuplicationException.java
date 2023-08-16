package com.dev.wanted.global.exception.verify;


import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class UserNicknameDuplicationException extends VerifyAbstractException {

    public UserNicknameDuplicationException(String message) {
        super("User Nickname is already exist" + message);
    }

    public UserNicknameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.UserNicknameDuplicationException.getErrorCode();
    }
}
