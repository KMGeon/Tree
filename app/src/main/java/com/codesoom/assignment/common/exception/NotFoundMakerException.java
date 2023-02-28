package com.codesoom.assignment.common.exception;

public class NotFoundMakerException extends RuntimeException{
    public NotFoundMakerException(Long id) {
        super("Product Id not found: " + id);
    }
}
