package com.example.jwtsecurity.dto;


import com.example.jwtsecurity.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDto {
    private String accessToken;
    private String refreshToken;

    private Long memberId;
    private String nickname;

    public static MemberLoginResponseDto convert(Member member, String accessToken, String refreshToken){
        return MemberLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberId(member.getMemberId())
                .nickname(member.getName())
                .build();
    }
}
