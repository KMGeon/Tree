package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
    void signup(MemberSignupRequest memberSignupRequest);
    MemberLoginResponse login(MemberLoginRequest memberLoginRequest, HttpServletResponse response);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
