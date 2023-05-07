package com.giggal.board.domain.member.application.impl;

import com.giggal.board.domain.admin.dto.response.GeoLocationDto;
import com.giggal.board.domain.member.application.MemberService;
import com.giggal.board.domain.member.dto.request.MemberLoginRequest;
import com.giggal.board.domain.member.dto.request.MemberSignupRequest;
import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.member.dto.response.MemberSignupResponse;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.domain.refresh.application.RefreshTokenService;
import com.giggal.board.domain.role.entity.Role;
import com.giggal.board.domain.role.enums.RoleEnum;
import com.giggal.board.domain.role.repositry.RoleRepository;
import com.giggal.board.global.exception.member.EmailDuplication;
import com.giggal.board.global.exception.member.NotFoundMemberEmail;
import com.giggal.board.global.jwt.util.JwtTokenizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenService refreshTokenService;

    public MemberServiceImpl(
            MemberRepository memberRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenizer jwtTokenizer,
            RefreshTokenService refreshTokenService
    ) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenizer = jwtTokenizer;
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * Returns the MemberSignupResponse With request
     *
     * @return 회원가입 성공을 하면 이메일, 이름을 리턴을 합니다.
     * @throws EmailDuplication
     */
    @Override
    @Transactional
    public MemberSignupResponse signUp(
            MemberSignupRequest request
            ,GeoLocationDto location
    ) {
        duplicationWithEmail(request);

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .ip(location.getHostAddress())
                .cityName(location.getCityName())
                .continentName(location.getContinentName())
                .countryName(location.getCountryName())
                .countryIsoCode(location.getCountryIsoCode())
                .subdivisionName(location.getSubdivisionName())
                .build();

        signupWithRole(member);

        return MemberSignupResponse.of(memberRepository.save(member));
    }

    /**
     * Returns login member with LoginRequest
     *
     * @param request
     * @return 로그인 성공하면 회원 아이디, JWT(Access, Refresh Token)을 리턴을 합니다.
     */
    @Override
    @Transactional
    public MemberLoginResponse login(MemberLoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundMemberEmail(request.getEmail()));

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

        refreshTokenService.addRefreshToken(refreshToken);
        return MemberLoginResponse.of(member, accessToken, refreshToken);
    }

    private void signupWithRole(Member member) {
        Optional<Role> userRole = roleRepository.findByName(RoleEnum.CREW.getRoleName());
        userRole.ifPresent(member::changeRole);
    }

    private void duplicationWithEmail(MemberSignupRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new EmailDuplication(request.getEmail());
        }
    }
}
