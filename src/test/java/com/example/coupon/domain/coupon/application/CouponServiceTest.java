package com.example.coupon.domain.coupon.application;

import com.example.coupon.domain.coupon.domain.Coupon;
import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import com.example.coupon.domain.coupon.repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;


    @Test
    @DisplayName("쿠폰 생성 테스트")
    public void couponCreateValidWithRequestDto() throws Exception {
        //given
        RequestDto requestDto = RequestDto.builder()
                .name("쿠폰")
                .amount(1000)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .build();
        //when
        CouponResponse coupon = couponService.createCoupon(requestDto);
        //Then
        assertThat(couponRepository.count()).isNotNull();
        assertThat(coupon.getName()).isEqualTo("쿠폰");
        assertThat(coupon.getAmount()).isEqualTo(1000);
    }


    @BeforeEach
    void setUp() {
        Coupon coupon = Coupon.builder()
                .name("쿠폰0")
                .amount(1000)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .build();

        couponRepository.saveAndFlush(coupon);
    }

    @Test
    @DisplayName("쿠폰 단일 Read 테스트")
    public void couponDetailValidWithRequestDto() throws Exception {
        //given
        Long id = 1L;
        //when
        CouponResponse couponResponse = couponService.getCoupon(id);
        //Then
        Assertions.assertThat(couponResponse.getName()).isEqualTo("쿠폰0");
        Assertions.assertThat(couponResponse.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("단일 쿠폰 수정 테스트")
    public void updateCouponValidWithDto() throws Exception {
        //given
        Long id = 1L;
        RequestDto requestDto = RequestDto.builder()
                .name("수정된 쿠폰")
                .amount(9999)
                .startDate(LocalDateTime.now().plusHours(1))
                .endDate(LocalDateTime.now().plusHours(3))
                .build();
        //when
        CouponResponse couponResponse = couponService.updateCoupon(id, requestDto);
        //Then
        Assertions.assertThat(couponResponse.getName()).isEqualTo("수정된 쿠폰");
        Assertions.assertThat(couponResponse.getAmount()).isEqualTo(9999);
    }
}