package dev.test.aswemake.global.exception.order;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class OrderAbstractException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public OrderAbstractException(String message) {
        super(message);
    }

    public OrderAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
