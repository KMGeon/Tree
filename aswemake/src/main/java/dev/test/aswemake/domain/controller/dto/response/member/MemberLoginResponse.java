package dev.test.aswemake.domain.controller.dto.response.member;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponse {
    private String accessToken;
    private String refreshToken;

    public static MemberLoginResponse of(String accessToken, String refreshToken) {
        return MemberLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
