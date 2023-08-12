package com.project.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.dto.request.Login;
import com.project.blog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
//    @DisplayName("로그인 성공 이후 세션 1개 생성")
    public void test() throws Exception {
        //given
        Login login = Login.builder()
                .email("test@naver.com")
                .password("1234")
                .build();
        String s = objectMapper.writeValueAsString(login);
        //when
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andDo(print());
        //Then
    }

    @DisplayName("회원가입 체크")
    @Test
    void validsignup() throws Exception {
        //given
        // when
        mockMvc.perform(
                        post("/auth/signup")
                                .contentType(APPLICATION_JSON)
                                .content("{\"email\":\"test\",\"password\":\"1234\",\"name\":\"김무건\"}")
                )
                .andExpect(status().isOk());
        //then
        //verify()
    }
}