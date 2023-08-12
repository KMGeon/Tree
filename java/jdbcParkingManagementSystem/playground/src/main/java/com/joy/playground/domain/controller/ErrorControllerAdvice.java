package com.joy.playground.domain.controller;

import com.joy.playground.global.exception.ErrorResponse;
import com.joy.playground.global.exception.UserEmailDuplicationException;
import com.joy.playground.global.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class ErrorControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserEmailDuplicationException.class)
    public ErrorResponse handleUserEmailIsAlreadyExisted(UserEmailDuplicationException e) {
        logger.error("User's email address is already existed:",e.getMessage());
        return new ErrorResponse("User's email address is already existed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse userNotFoundException(UserNotFoundException e) {
        logger.error("User's email address is already existed:",e.getMessage());
        return new ErrorResponse("User Not Found Exception");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ErrorResponse missingRequestHeaderException(MissingRequestHeaderException e) {
        logger.error("User's email address is already existed:",e.getMessage());
        return new ErrorResponse("MissingRequestHeaderException");
    }

}
