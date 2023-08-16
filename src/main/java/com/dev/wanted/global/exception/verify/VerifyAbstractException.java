package com.dev.wanted.global.exception.verify;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class VerifyAbstractException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public VerifyAbstractException(String message) {
        super(message);
    }

    public VerifyAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
