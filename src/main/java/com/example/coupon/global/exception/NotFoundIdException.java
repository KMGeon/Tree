package com.example.coupon.global.exception;

public class NotFoundIdException extends RuntimeException {

  public NotFoundIdException(Long id) {
    super("Not Found Id: " + id);
  }
}
