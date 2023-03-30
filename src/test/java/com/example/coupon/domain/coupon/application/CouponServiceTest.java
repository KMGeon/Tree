package com.example.coupon.domain.coupon.application;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.coupon.domain.coupon.domain.Coupon;
import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import com.example.coupon.domain.coupon.repository.CouponRepository;

import java.net.NoRouteToHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.coupon.global.exception.NotFoundIdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CouponServiceTest {

    private CouponService couponService;

    private final CouponRepository couponRepository = mock(CouponRepository.class);

    @BeforeEach
    void setUp() {
        couponService = new CouponService(couponRepository);

        given(couponRepository.save(any(Coupon.class))).will(invocation -> {
            Coupon source = invocation.getArgument(0);
            return Coupon.builder()
                    .id(1L)
                    .amount(source.getAmount())
                    .name(source.getName())
                    .startDate(source.getStartDate())
                    .endDate(source.getEndDate())
                    .build();
        });

        Coupon coupon = Coupon.builder()
                .id(1L)
                .amount(1000)
                .name("쿠폰1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .build();

        given(couponRepository.findByUseCouponFalse()).willReturn(List.of(coupon));
    }

    @Test
    @DisplayName("쿠폰 생성")
    public void createCouponWithValid() throws Exception {
        //given
        RequestDto requestDto = RequestDto.builder()
                .amount(1000)
                .name("쿠폰1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.of(2023, 3, 30, 12, 30))
                .build();
        //when
        CouponResponse coupon = couponService.createCoupon(requestDto);

        //Then
        assertThat(coupon.getName()).isEqualTo("쿠폰1");
        assertThat(coupon.getAmount()).isEqualTo(1000);

        verify(couponRepository).save(any(Coupon.class));
    }


    @Test
    @DisplayName("findByUseCouponFalse")
    public void findByUseCouponFalse() throws Exception {
        //when
        List<CouponResponse> couponResponseList = couponService.getCoupons();
        //Then
        assertThat(couponResponseList.get(0).getName()).isEqualTo("쿠폰1");
    }

    @Test
    @DisplayName("쿠폰 업데이트")
    public void updateCouponWithValid() throws Exception {
        //given
        given(couponRepository.findById(1L)).willReturn(Optional.of(Coupon.builder()
                .id(1L)
                .amount(1000)
                .name("쿠폰1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .build()));

        RequestDto requestDto = RequestDto.builder()
                .amount(2000)
                .name("쿠폰_update")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .build();

        //when
        CouponResponse couponResponse = couponService.updateCoupon(1L, requestDto);
        //Then
        assertThat(couponResponse.getAmount()).isEqualTo(2000);
        assertThat(couponResponse.getName()).isEqualTo("쿠폰_update");
    }

    @Test
    @DisplayName("쿠폰 업데이트 Exception")
    public void couponUpdateWithInvalid() {
        //when
        assertThatThrownBy(() -> couponService.updateCoupon(1L, RequestDto.builder()
                .amount(2000)
                .name("쿠폰_update")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .build()))
                .isInstanceOf(NotFoundIdException.class)
                .hasMessageContaining("Not Found Id: 1");
    }

    @Test
    @DisplayName("쿠폰 삭제")
    public void couponDeleteWithValid() throws Exception {
        //given
        Coupon coupon = Coupon.builder()
                .id(1L)
                .amount(1000)
                .name("쿠폰1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .build();

        given(couponRepository.findById(1L)).willReturn(Optional.of(coupon));
        //when
        couponService.deleteCoupon(1L);
        //Then
        verify(couponRepository).findById(1L);

        Coupon coupon1 = couponRepository.findById(1L)
                .orElseThrow(NoRouteToHostException::new);

        assertThat(coupon1.toString()).as("쿠폰 사용").isEqualTo("Coupon{useCoupon=true}");
    }
}