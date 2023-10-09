package com.example.jwtsecurity.service;


import com.example.jwtsecurity.domain.Member;
import com.example.jwtsecurity.domain.Role;
import com.example.jwtsecurity.dto.MemberLoginDto;
import com.example.jwtsecurity.dto.MemberLoginResponseDto;
import com.example.jwtsecurity.dto.MemberSignupDto;
import com.example.jwtsecurity.dto.MemberSignupResponseDto;
import com.example.jwtsecurity.jwt.util.JwtTokenizer;
import com.example.jwtsecurity.repository.MemberRepository;
import com.example.jwtsecurity.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenService refreshTokenService;

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    @Transactional
    public MemberLoginResponseDto login(MemberLoginDto loginDto){

        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(IllegalArgumentException::new);

        List<String> roles = member.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        String accessToken = jwtTokenizer.createAccessToken(
                member.getMemberId(),
                member.getEmail(), roles
        );

        String refreshToken = jwtTokenizer.createRefreshToken(
                member.getMemberId(),
                member.getEmail(), roles
        );
        refreshTokenService.addRefreshToken(refreshToken,member);

        return MemberLoginResponseDto.convert(member,accessToken,refreshToken);
    }

    @Transactional
    public MemberSignupResponseDto addMember(MemberSignupDto memberSignupDto) {

        Member member = Member.builder()
                .name(memberSignupDto.getName())
                .email(memberSignupDto.getEmail())
                .password(passwordEncoder.encode(memberSignupDto.getPassword()))
                .build();

        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        member.addRole(userRole.get());


        return MemberSignupResponseDto.convert(memberRepository.save(member));

    }

    @Transactional(readOnly = true)
    public Optional<Member> getMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
