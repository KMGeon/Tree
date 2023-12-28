package com.dev.wanted.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {
    private String accessToken;

    public static UserLoginResponseDto of(String accessToken) {
        return UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
