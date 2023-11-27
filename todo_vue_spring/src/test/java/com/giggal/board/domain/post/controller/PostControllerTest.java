package com.giggal.board.domain.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.domain.post.application.PostService;
import com.giggal.board.domain.post.dto.request.PostCreateRequest;
import com.giggal.board.global.jwt.util.JwtTokenizer;
import com.giggal.board.global.util.LoginUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.verify;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"local"})
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    @Autowired
    private MemberRepository memberRepository;

    private String token;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        token = jwtTokenizer.createAccessToken(1L, "test@test.com", List.of("ROLE_USER"));
    }

    @Test
    @DisplayName("게시글 작성 성공")
    void writePostWithValidAttributes() throws Exception {
        //given
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("제목1")
                .content("내용1")
                .build();

        LoginUserDto userDto = LoginUserDto.builder()
                .memberId(1L)
                .roles(List.of("ROLE_USER"))
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/post")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                                .content(objectMapper.writeValueAsString(postCreateRequest))
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());
        //then
        verify(postService).writePost(any(),any());
    }

}