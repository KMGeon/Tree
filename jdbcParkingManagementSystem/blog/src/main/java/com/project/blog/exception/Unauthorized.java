package com.project.blog.exception;

public class Unauthorized extends HodologException {

    private static final String MESSAGE = ExceptionMessage.AUTH.getMessage();


    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
