package com.project.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.dto.request.PostCreate;
import com.project.blog.dto.request.PostEdit;
import com.project.blog.dto.response.PostResponse;
import com.project.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MyTEST {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    private PostCreate request;

    private PostEdit postEdit;

    private PostResponse postResponse;

    @BeforeEach
    void setUp() {
        request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postResponse = PostResponse.builder()
                .content("content")
                .title("title")
                .build();

        postEdit = PostEdit.builder()
                .title("건무김")
                .content("제목")
                .build();

        given(postService.get(eq(1L))).willReturn(postResponse);
    }

    @Test
    @DisplayName("인증을 넣는다. get방식")
    void test() throws Exception {

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("authorization", "123"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("권한에 오류가 있습니다."))
                .andDo(print());

        verify(postService).write(any(PostCreate.class));
    }

    @Test
    @DisplayName("글 제목 수정")
    public void test7() throws Exception {

        mockMvc.perform(patch
                                ("/posts/1")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(postEdit))
//                                .header("authorization","hodolman")
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(postService).edit(eq(1L), any(PostEdit.class));

    }
}
