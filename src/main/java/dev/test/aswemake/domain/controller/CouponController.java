package dev.test.aswemake.domain.controller;

import dev.test.aswemake.domain.controller.dto.request.PayProductRequest;
import dev.test.aswemake.domain.controller.dto.request.coupon.CouponCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.PayCouponInfoResponse;
import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import dev.test.aswemake.domain.service.CouponService;
import dev.test.aswemake.global.argument.IfLogin;
import dev.test.aswemake.global.argument.LoginUserDto;
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
                .filter(req -> req.getCouponSaleStrategy() == CouponSaleStrategy.RATE && req.getSalePrice() > 100)
                .ifPresent(req -> {throw new RuntimeException("런타임");});

        couponService.createCoupon(loginUserDto, request);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MARKET')")
    public PayCouponInfoResponse payProductWithCoupon(@IfLogin LoginUserDto loginUserDto,
                                                      @RequestBody PayProductRequest payProductRequest) {
        return couponService.payProduct(loginUserDto, payProductRequest);
    }


}
