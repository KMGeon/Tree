package dev.test.aswemake.domain.controller;

import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;
import dev.test.aswemake.domain.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PermitAll
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signupMemberWithRoleUser(@Valid @RequestBody MemberSignupRequest memberSignupRequest) {
        log.info("email : {} , password : {}", memberSignupRequest.getEmail(), memberSignupRequest.getPassword());
        memberService.signup(memberSignupRequest);
    }

    @PermitAll
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberLoginResponse login(@Valid @RequestBody MemberLoginRequest memberLoginRequest,
                                     HttpServletResponse httpServletResponse) {
        log.info("email : {} , password : {}", memberLoginRequest.getEmail(), memberLoginRequest.getPassword());
        return memberService.login(memberLoginRequest, httpServletResponse);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MARKET')")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        memberService.logout(httpServletRequest, httpServletResponse);
    }
}
