package com.giggal.board.domain.refresh.service;

import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.domain.refresh.dto.request.RefreshTokenDto;
import com.giggal.board.domain.refresh.entity.RefreshToken;
import com.giggal.board.global.jwt.util.JwtTokenizer;
import com.giggal.board.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenService {
    void addRefreshToken(String refreshToken);

    void deleteRefreshToken(String refreshToken);

    MemberLoginResponse AccessTokenWithRefreshToken(RefreshTokenDto refreshTokenDto);
}
