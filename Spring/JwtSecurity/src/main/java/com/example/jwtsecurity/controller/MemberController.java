package com.example.jwtsecurity.controller;


import com.example.jwtsecurity.dto.*;
import com.example.jwtsecurity.service.MemberService;
import com.example.jwtsecurity.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberSignupResponseDto signup(@RequestBody MemberSignupDto memberSignupDto) {
        return memberService.addMember(memberSignupDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public MemberLoginResponseDto login(@RequestBody MemberLoginDto loginDto) {
        return memberService.login(loginDto);
    }


    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestBody RefreshTokenDto refreshTokenDto) {
        refreshTokenService.deleteRefreshToken(refreshTokenDto.getRefreshToken());
    }

    /*
    1. 전달받은 유저의 아이디로 유저가 존재하는지 확인한다.
    2. RefreshToken이 유효한지 체크한다.
    3. AccessToken을 발급하여 기존 RefreshToken과 함께 응답한다.
     */
    @PostMapping("/refreshToken")
    @ResponseStatus(HttpStatus.OK)
    public MemberLoginResponseDto requestRefresh(@RequestBody RefreshTokenDto refreshTokenDto) {
        return refreshTokenService.findRefreshToken(refreshTokenDto);
    }

}
