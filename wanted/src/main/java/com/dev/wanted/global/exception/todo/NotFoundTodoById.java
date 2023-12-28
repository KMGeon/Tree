package com.dev.wanted.global.exception.todo;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;
import com.dev.wanted.global.exception.user.UserAbstractException;

public class NotFoundTodoById extends UserAbstractException {

    public NotFoundTodoById(Long message) {
        super("Not Found Todo Id : " + message);
    }

    public NotFoundTodoById(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotFoundTodoById.getErrorCode();
    }
}
