package com.giggal.board.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giggal.board.domain.admin.dto.response.GeoLocationDto;
import com.giggal.board.domain.member.application.MemberService;
import com.giggal.board.domain.member.dto.request.MemberLoginRequest;
import com.giggal.board.domain.member.dto.request.MemberSignupRequest;
import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.member.dto.response.MemberSignupResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"local"})
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;



    @BeforeEach
    void setUp() {


        MemberSignupRequest request = MemberSignupRequest.builder()
                .email("test1234@naver.com")
                .password("test123!")
                .name("김무건")
                .build();

        given(memberService.signUp(any(MemberSignupRequest.class), any(GeoLocationDto.class)))
                .willReturn(MemberSignupResponse.builder()
                        .email("test1234@naver.com")
                        .name("김무건")
                        .build());

    }


    @Test
    @DisplayName("회원가입 유효성 검사 성공")
    void signupWithValidAttributes() throws Exception {
        //given
        // when
        mockMvc.perform(
                        post("/api/signup")
                                .contentType(APPLICATION_JSON)
                                .content("{" +
                                        "\"email\":\"test1234@naver.com\"," +
                                        "\"password\":\"test123!\",\"name\":\"김무건\"" +
                                        "}")

                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 유효성 검사 실패 (비밀번호 패턴)")
    void signupWithInvalidAttributes() throws Exception {
    //given

    // when
        mockMvc.perform(
                        post("/api/signup")
                                .contentType(APPLICATION_JSON)
                                .content("{" +
                                        "\"email\":\"test1234@naver.com\"," +
                                        "\"password\":\"1234\",\"name\":\"김무건\"" +
                                        "}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andDo(print());
    //then
    }

    @Test
    @DisplayName("로그인 유효성 검사 성공")
    void loginWithValidAttributes() throws Exception {
    //given
        given(memberService.login(any(MemberLoginRequest.class))).willReturn(MemberLoginResponse.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .email("test@email.com")
                .build());

        // when
        mockMvc.perform(
                        post("/api/login")
                                .contentType(APPLICATION_JSON)
                                .content("{\"email\":\"test@email.com\",\"password\":\"test1234!\"}")
                )
                .andExpect(status().isCreated())
                .andDo(print());
    //then
    verify(memberService).login(any(MemberLoginRequest.class));
    }
}