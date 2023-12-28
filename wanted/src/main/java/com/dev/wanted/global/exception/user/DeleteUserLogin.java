package com.dev.wanted.global.exception.user;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;

public class DeleteUserLogin extends UserAbstractException {

    public DeleteUserLogin(String message) {
        super("Delete User : " + message);
    }

    public DeleteUserLogin(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.DeleteUserLogin.getErrorCode();
    }
}
