package com.dev.wanted.global.exception;

import com.dev.wanted.global.exception.todo.TodoAbstractException;
import com.dev.wanted.global.exception.user.UserAbstractException;
import com.dev.wanted.global.exception.verify.VerifyAbstractException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("정규식에 적합하지 않습니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAbstractException.class)
    public ErrorResponse userAbstractException(UserAbstractException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        body.addValidation("UserException", "회원 관련 Exception");

        return ResponseEntity.status(statusCode)
                .body(body).getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerifyAbstractException.class)
    public ErrorResponse verifyAbstractException(VerifyAbstractException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        body.addValidation("VerifyException", "인증 관련 Exception");

        return ResponseEntity.status(statusCode)
                .body(body).getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TodoAbstractException.class)
    public ErrorResponse todoAbstractException(TodoAbstractException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        body.addValidation("TodoException", "todo 관련 Exception");

        return ResponseEntity.status(statusCode)
                .body(body).getBody();
    }
}
