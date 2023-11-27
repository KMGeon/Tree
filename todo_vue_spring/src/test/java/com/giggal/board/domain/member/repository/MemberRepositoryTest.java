package com.giggal.board.domain.member.repository;

import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.global.exception.member.NotFoundMemberEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
@ActiveProfiles({"local"})
class MemberRepositoryTest {

    private static final String VALID_EMAIL = "validEmail@email.com";
    private static final String VALID_PASSWORD = "test123!";


    @Autowired
    private MemberRepository memberRepository;


    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .name("김무건")
                .ip("1111.1111.1111")
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("회원 존재에 대한 유무")
    public void findMemberWithEmailValid() throws Exception {
        //given

        //when
        boolean result = memberRepository.existsByEmail(VALID_EMAIL);

        //Then
        assertThat(result).isEqualTo(true);
        assertThat(memberRepository.count()).isNotNull();
    }

    @Test
    @DisplayName("이메일 회원 찾기")
    public void findMemberOfValidEmail() throws Exception {
        //given

        //when
        Member member = memberRepository.findByEmail(VALID_EMAIL)
                .orElseThrow(() -> new NotFoundMemberEmail(VALID_EMAIL));
        //Then
        assertThat(member.getEmail()).isEqualTo(VALID_EMAIL);
    }

    @Test
    @DisplayName("잘못된 이메일 회원 찾기")
    public void findMemberOfInvalidEmail() {
        //given
        String fail_email = "fail@email.com";
        //Then
        assertThatThrownBy(() -> memberRepository.
                findByEmail(fail_email).
                orElseThrow(() -> new NotFoundMemberEmail(fail_email))
        )
                .isInstanceOf(NotFoundMemberEmail.class)
                .hasMessageContaining("User email is not found : fail@email.com");
    }
}