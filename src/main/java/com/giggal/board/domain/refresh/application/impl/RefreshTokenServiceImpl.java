package com.giggal.board.domain.refresh.service.impl;

import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.domain.refresh.dto.request.RefreshTokenDto;
import com.giggal.board.domain.refresh.service.RefreshTokenService;
import com.giggal.board.global.jwt.util.JwtTokenizer;
import com.giggal.board.global.redis.RedisService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final JwtTokenizer jwtTokenizer;
    private final MemberRepository memberRepository;
    private final RedisService redisService;

    @Override
    @Transactional
    public void addRefreshToken(String refreshToken) {
        redisService.setValues(refreshToken);
    }

    @Override
    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        redisService.delValues(refreshToken);
    }

    @Override
    public MemberLoginResponse AccessTokenWithRefreshToken(RefreshTokenDto refreshTokenDto) {
        String refreshToken = redisService.getValues(refreshTokenDto.getRefreshToken());

        Claims claims = jwtTokenizer.parseRefreshToken(refreshToken);
        Long userId = Long.valueOf((Integer) claims.get("memberId"));

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("userId"));

        List roles = claims.get("roles", List.class);
        String email = claims.getSubject();
        String accessToken = jwtTokenizer.createAccessToken(userId, email, roles);

        return MemberLoginResponse.of(member, accessToken, refreshTokenDto.getRefreshToken());
    }

}
