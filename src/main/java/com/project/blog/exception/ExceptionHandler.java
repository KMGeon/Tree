package com.project.blog.exception;

import com.project.blog.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse exceptionHandler(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = ErrorSet(400 , "잘못된 에러입니다.");

        for (FieldError fieldError : e.getFieldErrors())
            errorResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        return errorResponse;
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(HodologException.class)
    public ResponseEntity<ErrorResponse> hodologException(HodologException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode)
                .body(body);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ConversionFailedException.class)
    public void handleConversionFailedException(ConversionFailedException e) {
        logger.warn("ConversionFailedException : ", e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.warn("HttpMessageNotReadableException : ", e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(NumberFormatException.class)
    public void handleNumberFormatException(NumberFormatException e) {
        logger.warn("NumberFormatException : ", e);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warn("IllegalArgumentException : ", e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(BeanInstantiationException.class)
    public void handleBeanInstantiationException(BeanInstantiationException e) {
        logger.warn("BeanInstantiationException : ", e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException e) {
        logger.error("RuntimeException : ", e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        logger.error("Exception : ", e);
    }

    private static ErrorResponse ErrorSet(int code , String message) {
        return ErrorResponse.builder()
                .code(String.valueOf(code))
                .message(message)
                .build();
    }
}
