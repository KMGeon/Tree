package com.example.coupon.domain.coupon.repository;

import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponRepositoryCustom {
    Page<CouponResponse> searchPage(RequestDto requestDto, Pageable pageable);
}
