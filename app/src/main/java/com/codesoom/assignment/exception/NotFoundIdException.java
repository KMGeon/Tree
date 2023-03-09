package com.codesoom.assignment.exception;

public class NotFoundIdException extends RuntimeException{
    public NotFoundIdException(Long id) {
        super("Product Id not found: " + id);
    }
}