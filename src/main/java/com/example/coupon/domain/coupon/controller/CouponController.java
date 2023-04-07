package com.example.coupon.domain.coupon.controller;

import com.example.coupon.domain.coupon.application.CouponService;
import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import com.example.coupon.domain.coupon.domain.Coupon;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    /**
     * 새로운 쿠폰을 생성합니다.
     *
     * @param requestDto
     * @return
     * @valid name, amount
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CouponResponse createCoupon(
            @RequestBody RequestDto requestDto
    ) {
        return couponService.createCoupon(requestDto);
    }

    @GetMapping("/queryDSL")
    @ResponseStatus(HttpStatus.OK)
    public Page<CouponResponse> searchQueryDsl(RequestDto requestDto, Pageable pageable) {
        return this.couponService.searchQueryDsl(requestDto, pageable);
    }

    /**
     * 모든 쿠폰을 조회
     *
     * @return
     */
    @GetMapping("/getCoupons")
    @ResponseStatus(HttpStatus.OK)
    public List<CouponResponse> getCoupons(@RequestParam String codeType) {
        return couponService.getCoupons(codeType);
    }

    /**
     * 특정 id를 가진 쿠폰을 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/getCoupon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CouponResponse getCoupon(
            @PathVariable Long id
    ) {
        return couponService.getCoupon(id);
    }

    /**
     * 특정 id를 가진 쿠폰을 수정
     *
     * @param id
     * @param requestDto
     * @return
     * @Valid name, amount
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CouponResponse updateCoupon(
            @PathVariable Long id,
            @RequestBody @Valid RequestDto requestDto
    ) {
        return couponService.updateCoupon(id, requestDto);
    }


    /**
     * 특정 쿠폰을 삭제
     *
     * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        couponService.deleteCoupon(id);
    }

}

