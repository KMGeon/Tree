package com.dev.wanted.domain.controller;

import com.dev.wanted.domain.application.UserService;
import com.dev.wanted.domain.dto.request.LoginRequestDto;
import com.dev.wanted.domain.dto.request.UserSignupRequestDto;
import com.dev.wanted.domain.dto.response.UserLoginResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //사용자 회원가입
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto) {
        log.info("userSignupRequestDto>>{}", userSignupRequestDto);
        userService.signUp(userSignupRequestDto);
    }

    //로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginResponseDto login(
            @Valid @RequestBody LoginRequestDto loginRequestDto,
            HttpServletResponse response
    ) {
        log.info("loginRequestDto: {}", loginRequestDto.getAccount());
        return userService.login(loginRequestDto, response);
    }
}