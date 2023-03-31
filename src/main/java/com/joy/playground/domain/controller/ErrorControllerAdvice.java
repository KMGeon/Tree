package com.joy.playground.domain.controller;

import com.joy.playground.global.exception.ErrorResponse;
import com.joy.playground.global.exception.UserEmailDuplicationException;
import com.joy.playground.global.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class ErrorControllerAdvice {



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserEmailDuplicationException.class)
    public ErrorResponse handleUserEmailIsAlreadyExisted() {
        return new ErrorResponse("User's email address is already existed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse userNotFoundException() {
        return new ErrorResponse("User Not Found Exception");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ErrorResponse missingRequestHeaderException() {
        return new ErrorResponse("MissingRequestHeaderException");
    }

}
