package dev.test.aswemake.domain.controller;

import dev.test.aswemake.config.ControllerTest;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;
import dev.test.aswemake.enums.MemberTestEnum;
import dev.test.aswemake.global.exception.ErrorResponse;
import dev.test.aswemake.global.exception.StatusEnum.ErrorCustomEnum;
import dev.test.aswemake.global.exception.member.NotMatchMemberPassword;
import org.junit.jupiter.api.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MemberControllerTest extends ControllerTest {

    private MemberLoginRequest memberLoginRequest;
    private MemberSignupRequest memberSignupRequest;

    @BeforeEach
    void setUp() throws Exception {
        super.setup();

        memberLoginRequest = MemberLoginRequest.builder()
                .email(MemberTestEnum.VALID_EMAIL.getMessage())
                .password(MemberTestEnum.VALID_PASSWORD.getMessage())
                .build();

        memberSignupRequest = MemberSignupRequest.builder()
                .email(MemberTestEnum.VALID_EMAIL.getMessage())
                .password(MemberTestEnum.VALID_PASSWORD.getMessage())
                .build();

        given(memberService.login(eq(memberLoginRequest), any(HttpServletResponse.class))).willReturn(MemberLoginResponse.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build());
    }

    @AfterEach
    void cleanUp() {
        super.cleanup();
    }

    @DisplayName("POST /api/member 회원가입")
    @Nested
    class signup {
        @Test
        public void 회원가입_성공() throws Exception {
            memberMockApiCaller.signup(memberSignupRequest);
            assertThat(memberRepository.count()).isNotNull();
            verify(memberService).signup(any(MemberSignupRequest.class));
        }

        @Test
        public void 회원가입_실패_이메일_유효성_실패() throws Exception {
            //given
            MemberSignupRequest memberFailRequest = MemberSignupRequest.builder()
                    .password(MemberTestEnum.VALID_PASSWORD.getMessage())
                    .build();
            //when
            ApiResponse<ErrorResponse> response = memberMockApiCaller.signupFail(memberFailRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("email")).isEqualTo("이메일를 입력하세요")
            );
        }

        @Test
        public void 회원가입_실패_비밀번호_유효성_실패() throws Exception {
            //given
            MemberSignupRequest memberFailRequest = MemberSignupRequest.builder()
                    .email(MemberTestEnum.VALID_EMAIL.getMessage())
                    .build();
            //when
            ApiResponse<ErrorResponse> response = memberMockApiCaller.signupFail(memberFailRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("password")).isEqualTo("비밀번호를 입력하세요")
            );
        }
    }


    @DisplayName("POST /api/member/login 테스트")
    @Nested
    class login {
        @Test
        void 로그인_성공_테스트() throws Exception {
            ApiResponse<MemberLoginResponse> response = memberMockApiCaller.login(memberLoginRequest);

            assertThat(response.getStatus()).isEqualTo(201);
            assertThat(response.getBody().getAccessToken()).isEqualTo("accessToken");
            assertThat(response.getBody().getRefreshToken()).isEqualTo("refreshToken");

            verify(memberService).login(any(MemberLoginRequest.class), any(HttpServletResponse.class));
        }

        @Test
        public void 로그인_실패_미회원() throws Exception {
            MemberLoginRequest notRegisteredMember = MemberLoginRequest.builder()
                    .email(MemberTestEnum.INVALID_EMAIL.getMessage())
                    .password(MemberTestEnum.INVALID_PASSWORD.getMessage())
                    .build();

            given(memberService.login(eq(notRegisteredMember), any(HttpServletResponse.class)))
                    .willThrow(NotMatchMemberPassword.class);

            ApiResponse<ErrorResponse> response = memberMockApiCaller.loginFail(notRegisteredMember);

            assertAll(
                    () -> assertThat(response.getBody().getCode()).isEqualTo(String.valueOf(ErrorCustomEnum.NotMatchMemberPassword.getErrorCode())),
                    () -> assertThat(response.getBody().getDetailMessage().get("MemberException")).isEqualTo("회원 관련 Exception")
            );
        }

        @Test
        public void 로그인_실패_유효성검사_이메일() throws Exception {
            MemberLoginRequest memberLoginRequest = MemberLoginRequest.builder()
                    .email("test1234")
                    .password(MemberTestEnum.VALID_PASSWORD.getMessage())
                    .build();

            ApiResponse<ErrorResponse> response = memberMockApiCaller.loginFail(memberLoginRequest);


            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("email"))
                            .isEqualTo("must match \"^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$\"")
            );

        }

        @Test
        public void 로그인_실패_유효성검사_비밀번호() throws Exception {
            MemberLoginRequest memberLoginRequest = MemberLoginRequest.builder()
                    .email(MemberTestEnum.VALID_EMAIL.getMessage())
                    .password("1234")
                    .build();

            ApiResponse<ErrorResponse> response = memberMockApiCaller.loginFail(memberLoginRequest);

            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("password"))
                            .isEqualTo("must match \"^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$\"")
            );
        }

    }

    @Test
    @DisplayName("DELETE /api/member")
    public void 로그아웃_쿠키의_TOKEN을_삭제한다() throws Exception {
        mockMvc.perform(delete("/api/member")
                        .header("Authorization", "Bearer " + MARKET_TOKEN))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(memberService).logout(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }
}