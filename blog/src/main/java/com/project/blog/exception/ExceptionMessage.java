package com.project.blog.exception;

public enum ExceptionMessage {
    AUTH("권한에 오류가 있습니다."),
    ERROR("Error"),
    WARNING("Warning");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
