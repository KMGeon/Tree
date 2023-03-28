package com.example.coupon.domain.coupon.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.coupon.domain.coupon.application.CouponService;
import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(CouponController.class)
class CouponControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CouponService couponService;


  @BeforeEach
  void setUp() {
    LocalDateTime now = LocalDateTime.now();
    CouponResponse requestDto = CouponResponse.builder()
        .amount(1000)
        .name("쿠폰1")
        .startDate(now)
        .endDate(now.plusHours(1))
        .build();
    given(couponService.createCoupon(any(RequestDto.class))).willReturn(requestDto);

    given(couponService.getCoupons()).willReturn(List.of(requestDto));

    given(couponService.getCoupon(1L)).willReturn(requestDto);
  }

  @DisplayName("생성 테스트")
  @Test
  void create() throws Exception {
    // when
    mockMvc.perform(
            post("/coupon/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"쿠폰1\",\"amount\":1000}")
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("쿠폰1"))
        .andDo(print());
    //then
    verify(couponService).createCoupon(any(RequestDto.class));
  }

  @DisplayName("쿠폰 findall()")
  @Test
  void Get_getCoupons_findCouponWithValid() throws Exception {
    //given

    // when
    mockMvc.perform(
            get("/coupon/getCoupons")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andDo(print());
    //then
    //verify()
  }

  @DisplayName("getCoupon")
  @Test
  void GET_getcoupon_findByIdWithValid() throws Exception {
    //given

    // when
    mockMvc.perform(
            get("/coupon/getCoupon/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("쿠폰1"))
        .andExpect(jsonPath("$.amount").value(1000))
        .andDo(print());
    //then
    verify(couponService).getCoupon(eq(1L));
  }

}