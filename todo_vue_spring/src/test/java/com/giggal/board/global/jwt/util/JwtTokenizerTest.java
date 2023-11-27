package com.giggal.board.global.jwt.util;

import com.giggal.board.common.GeoReader;
import com.giggal.board.domain.role.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles({"local"})
public class JwtTokenizerTest {
    @Autowired
    private JwtTokenizer jwtTokenizer;

    private GeoReader geoReader;

    @BeforeEach
    void setUp() throws IOException {
        geoReader = new GeoReader();
    }


    @Test
    @DisplayName("access token 생성 테스트")
    public void testCreateAccessToken() {
        //given
        Long id = 1L;
        String email = "test@test.com";
        List<String> roles = List.of(RoleEnum.CREW.getRoleName());

        //when
        String accessToken = jwtTokenizer.createAccessToken(id, email, roles);

        //then
        Claims claims = jwtTokenizer.parseAccessToken(accessToken);
        assertThat(claims.get("memberId")).isEqualTo(1);
        assertThat(claims.get("roles")).isEqualTo(roles);
    }

    @Test
    @DisplayName("refresh token 생성 테스트")
    public void testCreateRefreshToken() {
        //given
        Long id = 1L;
        String email = "test@test.com";
        List<String> roles = List.of(RoleEnum.CREW.getRoleName());

        //when
        String refreshToken = jwtTokenizer.createRefreshToken(id, email, roles);

        //then
        Claims claims = jwtTokenizer.parseRefreshToken(refreshToken);
        assertThat(claims.get("memberId")).isEqualTo(1);
        assertThat(claims.get("roles")).isEqualTo(roles);
    }

    @Test
    @DisplayName("access token 파싱 테스트")
    public void testParseAccessToken() {
        //given
        Long id = 1L;
        String email = "test@test.com";
        List<String> roles = List.of(RoleEnum.CREW.getRoleName());

        String accessToken = jwtTokenizer.createAccessToken(id, email, roles);

        //when
        Claims claims = jwtTokenizer.parseAccessToken(accessToken);

        //then
        assertThat(claims.getSubject()).isEqualTo(email);
        assertThat(claims.get("memberId")).isEqualTo(1);
        assertThat(claims.get("roles")).isEqualTo(roles);
    }

    @Test
    @DisplayName("refresh token 파싱 테스트")
    public void testParseRefreshToken() {
        //given
        Long id = 1L;
        String email = "test@test.com";
        List<String> roles = List.of(RoleEnum.CREW.getRoleName());

        String refreshToken = jwtTokenizer.createRefreshToken(id, email, roles);

        //when
        Claims claims = jwtTokenizer.parseRefreshToken(refreshToken);

        //then
        assertThat(claims.getSubject()).isEqualTo(email);
        assertThat(claims.get("memberId")).isEqualTo(1);
        assertThat(claims.get("roles")).isEqualTo(roles);
    }

    @Test
    @DisplayName("access token에서 멤버 아이디 추출 테스트")
    public void testGetUserIdFromAccessToken() {
        //given
        Long id = 1L;
        String email = "test@test.com";
        List<String> roles = List.of(RoleEnum.CREW.getRoleName());

        String accessToken = jwtTokenizer.createAccessToken(id, email, roles);

        String authenticationToken = "Beer" + " " + accessToken;

        //when
        Long memberId = jwtTokenizer.getUserIdFromToken(authenticationToken);

        //then
        assertThat(memberId).isEqualTo(1);
    }
}