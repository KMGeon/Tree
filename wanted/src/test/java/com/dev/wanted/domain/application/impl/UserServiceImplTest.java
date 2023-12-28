package com.dev.wanted.domain.application.impl;

import com.dev.wanted.domain.application.UserService;
import com.dev.wanted.domain.dto.request.LoginRequestDto;
import com.dev.wanted.domain.dto.request.UserSignupRequestDto;
import com.dev.wanted.domain.dto.response.UserLoginResponseDto;
import com.dev.wanted.domain.entity.User;
import com.dev.wanted.domain.repository.UserRepository;
import com.dev.wanted.global.exception.user.InvalidPassword;
import com.dev.wanted.global.exception.user.NotFoundUserAccount;
import com.dev.wanted.global.exception.user.NotFoundUserId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .account("test1234@test.com")
                .password("test1234!")
                .build();

        userService.signUp(userSignupRequestDto);
    }


    @Test
    @DisplayName("회원가입 성공")
    public void signUpWithValid() throws Exception {
        //given
        String account = "test12345@test.com";
        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .account(account)
                .password("test1234!")
                .build();

        //when
        userService.signUp(userSignupRequestDto);
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new NotFoundUserAccount(account));
        //Then
        assertThat(user.getAccount()).isEqualTo(account);
    }


    @Test
    @DisplayName("로그인 계정을 찾을 수 없다.")
    public void NotFoundUserAccountWithLogin() throws Exception {
        //given
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .account("error1234@error.com")
                .password("1234")
                .build();
        //when
        //Then
        Assertions.assertThatThrownBy(() -> userService.login(loginRequestDto, null))
                .isInstanceOf(NotFoundUserAccount.class)
                .hasMessage("Not Found User Account : error1234@error.com");
    }


    @Test
    @DisplayName("로그인")
    public void testLogin() throws Exception{
        //given
        LoginRequestDto loginRequestDto =LoginRequestDto.builder()
                .account("test1234@test.com")
                .password("test1234!")
                .build();
        MockHttpServletResponse response = new MockHttpServletResponse();
        //when
        UserLoginResponseDto login = userService.login(loginRequestDto, response);
        //Then
        Assertions.assertThat(userRepository.count()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("로그인 비밀번호 불일치")
    public void InvalidLoginWithNotMatchPassword() throws Exception {
        //given
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .account("test1234@test.com")
                .password("test121134!")
                .build();
        //when
        MockHttpServletResponse response = new MockHttpServletResponse();
        //Then
        Assertions.assertThatThrownBy(() -> userService.login(loginRequestDto, response))
                .isInstanceOf(InvalidPassword.class)
                .hasMessage("InValid Match Password : test121134!");
    }
}