package com.example.coupon.domain.coupon.exception;

public class NotFoundIdException extends RuntimeException {

  public NotFoundIdException(Long id) {
    super("Not Found Id: " + id);
  }
}
