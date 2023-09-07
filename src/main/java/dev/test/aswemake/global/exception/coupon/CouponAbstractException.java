package dev.test.aswemake.global.exception.coupon;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class CouponAbstractException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public CouponAbstractException(String message) {
        super(message);
    }

    public CouponAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
