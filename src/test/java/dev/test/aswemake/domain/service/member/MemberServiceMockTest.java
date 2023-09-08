package dev.test.aswemake.domain.service.member;

import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;
import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.domain.entity.role.Role;
import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.repository.RoleRepository;
import dev.test.aswemake.domain.service.Impl.MemberServiceImpl;
import dev.test.aswemake.enums.MemberTestEnum;
import dev.test.aswemake.global.jwt.util.JwtTokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("회원가입_로그인_토큰의_일관성")
@ExtendWith(MockitoExtension.class)
class MemberServiceMockTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtTokenizer jwtTokenizer;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    private MemberSignupRequest memberSignupRequest;
    private Role userRole;

    private static final String ENCODE_PASSWORD ="PASSWORD1234!";

    @BeforeEach
    void setUp() {
        memberSignupRequest = MemberSignupRequest.builder()
                .email(MemberTestEnum.VALID_EMAIL.getMessage())
                .password(MemberTestEnum.VALID_PASSWORD.getMessage())
                .build();

        userRole = Role.builder()
                .name(RoleEnum.USER.getRoleName())
                .build();
    }

    @Test
    void 회원가입() {
        given(roleRepository.findByName(RoleEnum.USER.getRoleName())).willReturn(Optional.of(userRole));
        given(passwordEncoder.encode(memberSignupRequest.getPassword())).willReturn(ENCODE_PASSWORD);

        memberService.signup(memberSignupRequest);

        verify(roleRepository).findByName(RoleEnum.USER.getRoleName());
        verify(passwordEncoder).encode(memberSignupRequest.getPassword());
    }

    @Test
    public void 회원가입_TOKEN_일관성() throws Exception {
        
        MemberLoginRequest loginRequest = MemberLoginRequest.builder()
                .email(MemberTestEnum.VALID_EMAIL.getMessage())
                .password(MemberTestEnum.VALID_PASSWORD.getMessage())
                .build();


        given(memberRepository.findByEmail(MemberTestEnum.VALID_EMAIL.getMessage())).willReturn(Optional.of(Member.builder()
                .email(MemberTestEnum.VALID_EMAIL.getMessage())
                .password(ENCODE_PASSWORD)
                .roles(Collections.singleton(Role.builder()
                        .name(RoleEnum.USER.getRoleName())
                        .build()))
                .build()));

        given(passwordEncoder.matches(MemberTestEnum.VALID_PASSWORD.getMessage(), ENCODE_PASSWORD)).willReturn(true);

        given(jwtTokenizer.createAccessToken(any(), any(), any())).willReturn("ACCESS_TOKEN");
        given(jwtTokenizer.createRefreshToken(any(), any(), any())).willReturn("REFRESH_TOKEN");

        HttpServletResponse response = mock(HttpServletResponse.class);

        MemberLoginResponse loginResponse = memberService.login(loginRequest, response);

        assertNotNull(loginResponse);
        assertEquals("ACCESS_TOKEN", loginResponse.getAccessToken());
        assertEquals("REFRESH_TOKEN", loginResponse.getRefreshToken());
    }

}