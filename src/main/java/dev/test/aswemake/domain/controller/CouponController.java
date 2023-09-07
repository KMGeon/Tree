package dev.test.aswemake.domain.controller;

import dev.test.aswemake.domain.controller.dto.request.PayProductRequest;
import dev.test.aswemake.domain.controller.dto.request.coupon.CouponCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.PayCouponInfoResponse;
import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import dev.test.aswemake.domain.service.CouponService;
import dev.test.aswemake.global.argument.IfLogin;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.coupon.RateTooHighException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_MARKET')")
    public void createCoupon(@Valid @RequestBody CouponCreateRequest request,
                             @IfLogin LoginUserDto loginUserDto) {

        Optional.of(request)
                .filter(couponCreateRequest -> couponCreateRequest.getCouponSaleStrategy() == CouponSaleStrategy.RATE
                        && couponCreateRequest.getSalePrice() > 100)
                .ifPresent(req -> {
                    throw new RateTooHighException(request.getSalePrice());
                });

        couponService.createCoupon(loginUserDto, request);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MARKET')")
    public PayCouponInfoResponse payProductWithCoupon(@Valid @RequestBody PayProductRequest request,
                                                      @IfLogin LoginUserDto loginUserDto
    ) {
        return couponService.processPaymentWithCoupon(loginUserDto, request);
    }


}
