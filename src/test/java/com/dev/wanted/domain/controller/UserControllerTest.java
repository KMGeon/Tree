package com.dev.wanted.domain.controller;

import com.dev.wanted.domain.application.UserService;
import com.dev.wanted.domain.dto.request.LoginRequestDto;
import com.dev.wanted.domain.dto.request.UserSignupRequestDto;
import com.dev.wanted.domain.dto.response.UserLoginResponseDto;
import com.dev.wanted.domain.role.enums.RoleEnum;
import com.dev.wanted.global.jwt.util.JwtTokenizer;
import com.dev.wanted.global.util.LoginUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenizer jwtTokenizer;


    private static final String ACCOUNT = "test1234@test.com";
    private static final String PASSWORD = "test1234!";
    private static String token;
    private static final String ACCESS_TOKEN = "123412341234";

    private static String tokenHeader;
    private static String tokenCookie;

    @BeforeEach
    void setUp() {
        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .account(ACCOUNT)
                .password(PASSWORD)
                .build();

        LoginUserDto loginUserDto = LoginUserDto.builder()
                .userId(1L)
                .roles(List.of(RoleEnum.ADMIN.getRoleName()))
                .build();

        token = jwtTokenizer.createAccessToken(1L, ACCOUNT, List.of(RoleEnum.ADMIN.getRoleName()));


        UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                .accessToken(ACCESS_TOKEN)
                .build();
        given(userService.login(any(LoginRequestDto.class), any(HttpServletResponse.class))).willReturn(userLoginResponseDto);

        tokenHeader = jwtTokenizer.createAccessToken(1L, ACCOUNT, List.of(RoleEnum.USER.getRoleName()));
        tokenCookie = jwtTokenizer.createRefreshToken(1L, ACCOUNT, List.of(RoleEnum.USER.getRoleName()));
    }

    @Test
    @DisplayName("회원가입 유효성 검사 성공")
    void signupWithValidAttributes() throws Exception {
        //given
        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .account(ACCOUNT)
                .password(PASSWORD)
                .build();
        // when
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userSignupRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("회원가입 유효성 검사 실패")
    void signupInvalid() throws Exception {
        //given
        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .account("error")
                .password(PASSWORD)
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userSignupRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("정규식에 적합하지 않습니다."))
                .andDo(print());
        //then
        //verify()
    }

    @Test
    @DisplayName("회원가입 유효성 검사 실패")
    void signupInvalidAboutPassword() throws Exception {
        //given
        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .account(ACCOUNT)
                .password("1234")
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userSignupRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("정규식에 적합하지 않습니다."))
                .andDo(print());
        //then
    }

    @Test
    @DisplayName("로그인 성공")
    void userLoginValidWithParameter() throws Exception {
        //given
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .account("test1234@tset.com")
                .password("test1234!")
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.accessToken").value("123412341234"))
                .andDo(print());
        //then
        verify(userService).login(any(LoginRequestDto.class), any(HttpServletResponse.class));
    }

    @Test
    @DisplayName("로그인 실패 - 정규식 예외")
    void userLoginInValidWithNotMatchPatten() throws Exception {
        //given
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .account("test1234")
                .password("test1234!")
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("정규식에 적합하지 않습니다."))
                .andDo(print());
        //then
    }

}