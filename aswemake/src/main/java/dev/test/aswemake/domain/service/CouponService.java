package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.controller.dto.request.PayProductRequest;
import dev.test.aswemake.domain.controller.dto.request.coupon.CouponCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.PayCouponInfoResponse;
import dev.test.aswemake.global.argument.LoginUserDto;

public interface CouponService {
    void createCoupon(LoginUserDto loginUserDto, CouponCreateRequest couponCreateRequest);
    PayCouponInfoResponse processPaymentWithCoupon(LoginUserDto loginUserDto, PayProductRequest payProductRequest);
}
