package com.giggal.board.domain.refresh.application;

import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.refresh.dto.request.RefreshTokenDto;

public interface RefreshTokenService {
    void addRefreshToken(String refreshToken);

    void deleteRefreshToken(String refreshToken);

    MemberLoginResponse AccessTokenWithRefreshToken(RefreshTokenDto refreshTokenDto);
}
