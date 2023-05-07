package com.giggal.board.domain.member.application.impl;

import com.giggal.board.common.GeoReader;
import com.giggal.board.common.GeoService;
import com.giggal.board.domain.admin.dto.response.GeoLocationDto;
import com.giggal.board.domain.enums.TestEnum;
import com.giggal.board.domain.member.application.MemberService;
import com.giggal.board.domain.member.dto.request.MemberLoginRequest;
import com.giggal.board.domain.member.dto.request.MemberSignupRequest;
import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.member.dto.response.MemberSignupResponse;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.global.exception.member.EmailDuplication;
import com.giggal.board.global.exception.member.NotFoundMemberEmail;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Continent;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;

import static com.giggal.board.domain.admin.entity.Admin.getIpAddress;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Transactional
@SpringBootTest
@ActiveProfiles({"local"})
class MemberServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private GeoService geoService;

    private GeoReader geoReader;

    @BeforeEach
    void setUp() throws IOException {
        geoReader = new GeoReader();
    }


    @Test
    @DisplayName("회원가입")
    public void signupWithValidRequest() throws Exception {
        //given
        geoReader = new GeoReader();
        GeoLocationDto location = geoService.findLocation(getIpAddress());

        MemberSignupRequest request = MemberSignupRequest.builder()
                .email(TestEnum.VALID_EMAIL.getName())
                .password(TestEnum.VALID_PASSWORD.getName())
                .name(TestEnum.NAME.getName())
                .build();
        //when
        MemberSignupResponse response = memberService.signUp(request, location);
        //Then
        assertThat(response.getEmail()).isEqualTo(TestEnum.VALID_EMAIL.getName());
        assertThat(response.getName()).isEqualTo(TestEnum.NAME.getName());
    }

    @Nested
    @DisplayName("회원가입 중복 이메일 예외로직")
    class duplicate {

        @BeforeEach
        void setUp() {
            Member member = Member.builder()
                    .email(TestEnum.DUPLICATE_EMAIL.getName())
                    .password(TestEnum.INVALID_EMAIL.getName())
                    .name(TestEnum.NAME.getName())
                    .build();

            memberRepository.save(member);
        }

        @Test
        @DisplayName("중복 회원에 대한 예외처리")
        public void duplicateMemberWithInValidEmail() {
            //given
            GeoLocationDto location = geoService.findLocation(getIpAddress());
            MemberSignupRequest request = MemberSignupRequest.builder()
                    .email(TestEnum.DUPLICATE_EMAIL.getName())
                    .password(TestEnum.INVALID_EMAIL.getName())
                    .name(TestEnum.NAME.getName())
                    .build();
            //Then
            assertThatThrownBy(() -> memberService.signUp(request, location))
                    .isInstanceOf(EmailDuplication.class)
                    .hasMessage("User email is already existed:duplicate@email.com");
        }
    }

    @Nested
    @DisplayName("로그인 공통 테스트" +
            "Redis 서버를 실행을 해야지 성공적으로 테스트")
    class memberLogin {

        @BeforeEach
        void setUp() throws IOException {

            geoReader = new GeoReader();
            Member member = Member.builder()
                    .email(TestEnum.VALID_EMAIL.getName())
                    .password(TestEnum.VALID_PASSWORD.getName())
                    .name(TestEnum.NAME.getName())
                    .build();

            memberRepository.save(member);
        }

        @Test
        @DisplayName("로그인 성공 테스트")
        public void ValidLoginWithRequest() throws Exception{
            //given
            MemberLoginRequest request = MemberLoginRequest.builder()
                    .email(TestEnum.VALID_EMAIL.getName())
                    .password(TestEnum.VALID_PASSWORD.getName())
                    .build();
            //when
            MemberLoginResponse login = memberService.login(request);
            //Then
            assertThat(login.getName()).isEqualTo(TestEnum.NAME.getName());
            assertThat(login.getEmail()).isEqualTo(TestEnum.VALID_EMAIL.getName());
        }

        @Test
        @DisplayName("회원가입 이전 회원 로그인 실패")
        public void InvalidLoginWithRequest(){
            //given
            MemberLoginRequest request = MemberLoginRequest.builder()
                    .email(TestEnum.INVALID_EMAIL.getName())
                    .password(TestEnum.INVALID_PASSWORD.getName())
                    .build();
            //Then
                assertThatThrownBy(() -> memberService.login(request))
                        .isInstanceOf(NotFoundMemberEmail.class)
                        .hasMessage("User email is not found : Invalid");
        }
    }

}