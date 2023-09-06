package dev.test.aswemake.domain.service;

import dev.test.aswemake.config.ApplicationTestBase;
import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;
import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.enums.MemberTestEnum;
import dev.test.aswemake.global.exception.member.NotFoundMemberEmail;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static dev.test.aswemake.global.jwt.util.JwtTokenizer.REFRESH_TOKEN;
import static dev.test.aswemake.global.jwt.util.JwtTokenizer.REFRESH_TOKEN_EXPIRE_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@Transactional
class MemberServiceImplTest extends ApplicationTestBase {

    @Test
    public void 회원가입() throws Exception {
        //given
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .email(MemberTestEnum.VALID_EMAIL.getMessage())
                .password(MemberTestEnum.VALID_PASSWORD.getMessage())
                .build();
        //when
        memberService.signup(memberSignupRequest);
        //Then
        Member member = memberRepository.findByEmail(MemberTestEnum.VALID_EMAIL.getMessage())
                .orElseThrow(() -> new NotFoundMemberEmail(MemberTestEnum.VALID_EMAIL.getMessage()));

        boolean matches = passwordEncoder.matches(MemberTestEnum.VALID_PASSWORD.getMessage(), member.getPassword());

        assertAll(
                () -> assertThat(memberRepository.count()).isNotNull(),
                () -> assertThat(member.getEmail()).isEqualTo(MemberTestEnum.VALID_EMAIL.getMessage()),
                () -> assertThat(member.getRoles()).isNotNull(),
                () -> assertThat(member.getPassword()).isNotEqualTo(MemberTestEnum.VALID_PASSWORD.getMessage()),
                () -> assertThat(matches).isTrue()
        );
    }

    @Test
    public void 로그인() throws Exception {
        //given
        MemberLoginRequest memberLoginRequest = MemberLoginRequest.builder()
                .email("market@market.com")
                .password("market1234!")
                .build();

        HttpServletResponse response = new MockHttpServletResponse();

        Cookie cookie = new Cookie(REFRESH_TOKEN, "REFRESH_TOKEN");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(Math.toIntExact(REFRESH_TOKEN_EXPIRE_COUNT));
        cookie.setPath("/");
        response.addCookie(cookie);

        //when
        MemberLoginResponse login = memberService.login(memberLoginRequest, response);
        //Then
        assertThat(login.getAccessToken()).isNotNull();
        assertThat(login.getRefreshToken()).isNotNull();
    }

}