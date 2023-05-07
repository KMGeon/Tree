package com.giggal.board.domain.member.controller;

import com.giggal.board.common.GeoService;
import com.giggal.board.domain.admin.dto.response.GeoLocationDto;
import com.giggal.board.domain.member.application.MemberService;
import com.giggal.board.domain.member.dto.request.MemberLoginRequest;
import com.giggal.board.domain.member.dto.request.MemberSignupRequest;
import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.refresh.application.RefreshTokenService;
import com.giggal.board.domain.refresh.dto.request.RefreshTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.giggal.board.domain.admin.entity.Admin.getIpAddress;

@Slf4j
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    private final GeoService geoService;

    public MemberController(
            MemberService memberService,
            RefreshTokenService refreshTokenService,
            GeoService geoService
    ) {
        this.memberService = memberService;
        this.refreshTokenService = refreshTokenService;
        this.geoService = geoService;
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpWithRole(
            @Valid
            @RequestBody MemberSignupRequest request
    ) {
        log.info(String.format("request:>>{%s}", request));
        GeoLocationDto location = geoService.findLocation(getIpAddress());
        memberService.signUp(request, location);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberLoginResponse login(
            @Valid
            @RequestBody MemberLoginRequest request
    ) {
        log.info(String.format("request:>>{%s}", request));
        return memberService.login(request);
    }

    @DeleteMapping("logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(
            @RequestBody RefreshTokenDto refreshTokenDto
    ) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        log.info(String.format("Refresh Token:>>{%s}", refreshToken));
        refreshTokenService.deleteRefreshToken(refreshToken);
    }
}
