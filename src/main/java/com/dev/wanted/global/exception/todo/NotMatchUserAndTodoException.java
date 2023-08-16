package com.dev.wanted.global.exception.todo;

import com.dev.wanted.global.exception.StatusEnum.ErrorCustomEnum;
import com.dev.wanted.global.exception.user.UserAbstractException;

public class NotMatchUserAndTodoException extends UserAbstractException {

    public NotMatchUserAndTodoException(Long message) {
        super("해당 회원이 작성한 Todo가 아닙니다. " + message);
    }

    public NotMatchUserAndTodoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return ErrorCustomEnum.NotMatchUserAndTodo.getErrorCode();
    }
}
