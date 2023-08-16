package com.dev.wanted.global.exception.user;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class UserAbstractException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public UserAbstractException(String message) {
        super(message);
    }

    public UserAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
