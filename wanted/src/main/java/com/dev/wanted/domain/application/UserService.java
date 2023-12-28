package com.dev.wanted.domain.application;


import com.dev.wanted.domain.dto.request.LoginRequestDto;
import com.dev.wanted.domain.dto.request.UserSignupRequestDto;
import com.dev.wanted.domain.dto.response.UserLoginResponseDto;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    void signUp(UserSignupRequestDto userSignupRequestDto);

    UserLoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response);
}
