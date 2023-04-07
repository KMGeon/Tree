package com.example.coupon.domain.coupon.controller;

import com.example.coupon.domain.coupon.application.CouponService;
import com.example.coupon.domain.coupon.domain.Coupon;
import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CouponController.class)
@AutoConfigureRestDocs
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponService couponService;

    @Autowired
    private ObjectMapper objectMapper;


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

        given(couponService.getCoupons(codeType)).willReturn(List.of(requestDto));

        given(couponService.getCoupon(1L)).willReturn(requestDto);

        given(couponService.updateCoupon(eq(1L), any(RequestDto.class)))
                .will(invocation -> {
                    RequestDto dto = invocation.getArgument(1);//파라미터 2번 requestdto를 의미한다.
                    return CouponResponse.builder()
                            .name(dto.getName())
                            .amount(dto.getAmount())
                            .startDate(dto.getStartDate())
                            .endDate(dto.getEndDate())
                            .build();
                });
    }


    @DisplayName("생성 테스트")
    @Test
    void create() throws Exception {
        // when
        mockMvc.perform(
                        post("/coupon/create")
                                .contentType(APPLICATION_JSON)
                                .content("{\"name\":\"쿠폰1\",\"amount\":1000}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("쿠폰1"))
                .andDo(document("get-product"));
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
                                .contentType(APPLICATION_JSON)
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
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("쿠폰1"))
                .andExpect(jsonPath("$.amount").value(1000))
                .andDo(print());
        //then
        verify(couponService).getCoupon(eq(1L));
    }

    @DisplayName("Patch_updateCoupon_Valid")
    @Test
    void updateCouponWithValid() throws Exception {
        //given
        Long id = 1L;
        // when
        mockMvc.perform(
                        patch("/coupon/{id}", id)
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(Coupon.builder()
                                        .id(1L)
                                        .name("쿠폰111")
                                        .amount(20000)
                                        .startDate(LocalDateTime.now())
                                        .endDate(LocalDateTime.now().plusHours(3))
                                        .build()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("쿠폰111"))
                .andExpect(jsonPath("$.amount").value(20000))
                .andDo(print());
        //then
        verify(couponService).updateCoupon(eq(1L), any(RequestDto.class));
    }

    @DisplayName("delete")
    @Test
    void deleteWithValid() throws Exception {
        //given
        Long id = 1L;
        // when
        mockMvc.perform(
                        delete("/coupon/{id}", id)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andDo(print());
        //then
        verify(couponService).deleteCoupon(1L);
    }

}