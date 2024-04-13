package dev.test.aswemake.domain.controller.exceptionHandler;

import dev.test.aswemake.global.exception.ErrorResponse;
import dev.test.aswemake.global.exception.coupon.CouponAbstractException;
import dev.test.aswemake.global.exception.member.MemberAbstractException;
import dev.test.aswemake.global.exception.order.OrderAbstractException;
import dev.test.aswemake.global.exception.product.ProductAbstractException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
            response.addDetailMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message(e.getMessage())
                .build();

        response.addDetailMessage("유니크 키 중복 데이터 삽입", e.getMessage());

        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse AccessDeniedException(AccessDeniedException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("403")
                .message("Access Denied: " + e.getMessage())
                .build();
        response.addDetailMessage("Spring Security", "권한이 일치하지 않습니다.");
        return response;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberAbstractException.class)
    public ErrorResponse MemberException(MemberAbstractException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        body.addDetailMessage("MemberException", "회원 관련 Exception");

        return ResponseEntity.status(statusCode)
                .body(body).getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductAbstractException.class)
    public ErrorResponse ProductAbstractException(ProductAbstractException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        body.addDetailMessage("ProductException", "상품 관련 Exception");

        return ResponseEntity.status(statusCode)
                .body(body).getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CouponAbstractException.class)
    public ErrorResponse CouponAbstractException(CouponAbstractException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        body.addDetailMessage("CouponAbstractException", "쿠폰 관련 Exception");

        return ResponseEntity.status(statusCode)
                .body(body).getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderAbstractException.class)
    public ErrorResponse OrderAbstractException(OrderAbstractException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        body.addDetailMessage("OrderAbstractException", "주문 관련 Exception");

        return ResponseEntity.status(statusCode)
                .body(body).getBody();
    }
}
