package com.example.coupon.domain.coupon.controller;

import com.example.coupon.domain.coupon.dto.response.ErrorResponse;
import com.example.coupon.domain.coupon.exception.NotFoundIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NotFoundIdException.class)
  public ErrorResponse notFoundIdException() {
    return new ErrorResponse("Not found id");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    return new ErrorResponse("MethodArgumentNotValidException");
  }

}
