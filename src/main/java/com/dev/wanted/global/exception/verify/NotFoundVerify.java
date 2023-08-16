package com.dev.wanted.global.exception.verify;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;
import com.dev.wanted.global.exception.user.UserAbstractException;

public class NotFoundVerify extends UserAbstractException {

    public NotFoundVerify(String message) {
        super("Not Found Verify : " + message);
    }

    public NotFoundVerify(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundVerify.getErrorCode();
    }
}
