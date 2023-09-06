package dev.test.aswemake.global.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, String> detailMessage;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage != null ? detailMessage : new HashMap<>();
    }

    public void addDetailMessage(String fieldName, String errorMessage) {
        this.detailMessage.put(fieldName, errorMessage);
    }
}
