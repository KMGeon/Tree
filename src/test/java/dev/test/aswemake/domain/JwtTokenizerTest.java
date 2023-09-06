package dev.test.aswemake.domain;

import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.global.jwt.util.JwtTokenizer;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtTokenizerTest {
    private static final String secretKey = "0123456789012345678901234567890123456789";
    private static final String refreshKey = "0123456789012345678901234567890123456789";

    private JwtTokenizer jwtTokenizer;

    @BeforeEach
    void setUp() {
        jwtTokenizer = new JwtTokenizer(secretKey, refreshKey);
    }

    @Test
    @DisplayName("Token 생성하기")
    public void createValidToken() throws Exception {
        // given
        Long memberId = 1L;
        String email = "test1234@email.com";
        List<String> roles = List.of(RoleEnum.USER.getRoleName());

        // when
        String accessToken = jwtTokenizer.createAccessToken(memberId, email, roles);

        // Then
        assertThat(accessToken).isNotBlank();

        // 토큰 파싱하여 클레임 값 확인
        Claims claims = jwtTokenizer.parseAccessToken(accessToken);
        assertThat(claims.getSubject()).isEqualTo(email);
        assertThat(claims.get("memberId", Long.class)).isEqualTo(memberId);
        assertThat(claims.get("roles", List.class)).isEqualTo(roles);
    }

    @Test
    @DisplayName("토큰 생성하기")
    public void createTokenTest() {
        // given
        Long id = 1L;
        String email = "test@example.com";
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        Long expire = 3600000L; // 1 hour
        byte[] secretKey = "0123456789012345678901234567890123456789".getBytes();

        // when
        String token = jwtTokenizer.createToken(id, email, roles, expire, secretKey);

        // then
        assertThat(token).isNotBlank();
    }

    @Test
    @DisplayName("트레이드 오프에 따른 Date 토큰 생성하기")
    public void createToken() throws Exception{

        // given
        Long id = 1L;
        String email = "test@example.com";
        List<String> roles = Arrays.asList(RoleEnum.MARKET.getRoleName(), RoleEnum.MARKET.getRoleName());

        Long expire = 3600000L;
        byte[] secretKey = "0123456789012345678901234567890123456789".getBytes();
        Date date = new Date(1656789000000L);

        // when
        String token = jwtTokenizer.createTokenWithDate(id, email, roles, expire, secretKey, date);

        //Then
        assertThat(token).isEqualTo("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwibWVtYmVy" +
                "SWQiOjEsInJvbGVzIjpbIlJPTEVfTUFSS0VUIiwiUk9MRV9NQVJLRVQiXSwiaWF0IjoxNjU2Nzg5MDAwLCJleHAiOjE2NTY3OTI2MDB9.uYrs6elfdx7zMJGZahZ0JpUQMD-yUdbotzjpy0N2zNc");
    }
}