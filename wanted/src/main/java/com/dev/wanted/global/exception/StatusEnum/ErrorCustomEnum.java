package com.dev.wanted.global.exception.StatusEnum;

import lombok.Getter;

@Getter
public enum ErrorCustomEnum {

    AccountDuplication(1000),
    NotFoundUserId(1001),
    NotFoundUserAccount(1002),
    InvalidPassword(1003),
    DeleteUserLogin(1004),
    NotFoundTodoById(2000),
    NotMatchUserAndTodo(2001),
    NotFoundVerify(3000),
    DuplicateCrnException(3001),
    InvalidCrnException(3002),
    UserAccountDuplicationException(3003),
    UserNicknameDuplicationException(3004);

    private final int errorCode;

    ErrorCustomEnum(int errorCode) {
        this.errorCode = errorCode;
    }


}
