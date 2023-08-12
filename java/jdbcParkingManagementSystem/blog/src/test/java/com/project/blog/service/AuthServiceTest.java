package com.project.blog.service;

import com.project.blog.domain.User;
import com.project.blog.dto.request.SignUp;
import com.project.blog.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    private static final String VALID_EMAIL = "mugeon1203@naver.com";
    private static final String VALID_PASSWORD = "1234";


    @Test
    @DisplayName("회원가입")
    public void createUser() throws Exception{
        //given
        SignUp signUp = SignUp.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .name("김무건")
                .build();
        //when
        authService.signUp(signUp);
        //Then
        User next = userRepository.findAll().iterator().next();
        assertThat(next.getId()).isEqualTo(1L);
        assertThat(next.getName()).isEqualTo("김무건");
    }

    @Test
    @DisplayName("이메일 중복")        
    public void Invalid_email() throws Exception{
        //given
        SignUp signUp = SignUp.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .name("김무건")
                .build();
        authService.signUp(signUp);

        assertThatThrownBy(()->authService.signUp(signUp))
                .isInstanceOf(IllegalArgumentException.class);
    }
}