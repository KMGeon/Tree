package dev.test.aswemake.global.exception.product;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ProductAbstractException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public ProductAbstractException(String message) {
        super(message);
    }

    public ProductAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
