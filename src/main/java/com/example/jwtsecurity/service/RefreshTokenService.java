package com.example.jwtsecurity.service;


import com.example.jwtsecurity.domain.Member;
import com.example.jwtsecurity.domain.RefreshToken;
import com.example.jwtsecurity.dto.MemberLoginResponseDto;
import com.example.jwtsecurity.dto.RefreshTokenDto;
import com.example.jwtsecurity.jwt.util.JwtTokenizer;
import com.example.jwtsecurity.repository.MemberRepository;
import com.example.jwtsecurity.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenizer jwtTokenizer;
    private final MemberRepository memberRepository;

    @Transactional
    public RefreshToken addRefreshToken(String refreshToken, Member member) {

        RefreshToken token = RefreshToken.builder()
                .value(refreshToken)
                .memberId(member.getMemberId())
                .build();

        return refreshTokenRepository.save(token);
    }

    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.findByValue(refreshToken).ifPresent(refreshTokenRepository::delete);
    }

    @Transactional(readOnly = true)
    public MemberLoginResponseDto findRefreshToken(RefreshTokenDto refreshTokenDto) {
        RefreshToken refreshToken = refreshTokenRepository.findByValue(refreshTokenDto.getRefreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));

        Claims claims = jwtTokenizer.parseRefreshToken(refreshToken.getValue());
        Long userId = Long.valueOf((Integer) claims.get("userId"));

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        List roles = (List) claims.get("roles");
        String email = claims.getSubject();
        String accessToken = jwtTokenizer.createAccessToken(userId, email, roles);


        return  MemberLoginResponseDto.convert(member,accessToken,refreshTokenDto.getRefreshToken());
    }



}
