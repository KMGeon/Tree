package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;
import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.domain.entity.role.Role;
import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.repository.RoleRepository;
import dev.test.aswemake.domain.service.MemberService;
import dev.test.aswemake.global.exception.member.NotFoundMemberEmail;
import dev.test.aswemake.global.exception.member.NotMatchMemberPassword;
import dev.test.aswemake.global.jwt.util.JwtTokenizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;

    public MemberServiceImpl(
            MemberRepository memberRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenizer jwtTokenizer
    ) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenizer = jwtTokenizer;
    }

    /**
     * 회원가입
     * 회원을 REQUEST에 맞게 USER 권한을 추가하여 생성한다.
     */
    @Override
    @Transactional
    public void signup(MemberSignupRequest memberSignupRequest) {
        Member member = Member.signupMember(memberSignupRequest, passwordEncoder);

        roleRepository.findByName(RoleEnum.USER.getRoleName())
                .ifPresent(member::changeRole);

        memberRepository.save(member);
    }

    /**
     * 로그인
     * 회원의 아이디를 찾아서 비밀번호의 일치를 판단하여 JWT 토큰을 반환한다.
     */
    @Override
    @Transactional
    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest, HttpServletResponse response) {

        Member member = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new NotFoundMemberEmail(memberLoginRequest.getEmail()));

        Optional.of(!passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword()))
                .filter(passwordNotMatched -> passwordNotMatched)
                .ifPresent(passwordNotMatched -> {
                    throw new NotMatchMemberPassword(memberLoginRequest.getPassword());
                });

        return createToken(response, member);
    }


    /**
     * 로그아웃
     * 쿠키의 refresh Token을 삭제한다. 실제로 로그아웃을 구현을 하려면 End Point에서 accessToken을 삭제한다.
     * refreshToken을 기반으로 accessToken 재발급은 생략
     */
    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        jwtTokenizer.deleteRefreshTokenInCookie(request, response);
    }


    private MemberLoginResponse createToken(HttpServletResponse response, Member member) {

        List<String> roles = member.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        String accessToken = jwtTokenizer.createAccessToken(
                member.getId(),
                member.getEmail(),
                roles
        );

        String refreshToken = jwtTokenizer.createRefreshToken(
                member.getId(),
                member.getEmail(),
                roles
        );

        jwtTokenizer.addTokenCookie(response, refreshToken);

        return MemberLoginResponse.of(accessToken, refreshToken);
    }
}
