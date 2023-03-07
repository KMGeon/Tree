package com.project.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.domain.Post;
import com.project.blog.dto.PostCreate;
import com.project.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void clean(){
        this.postRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {
        //given


        PostCreate postCreate = PostCreate.builder().
                title("타이틀")
                .content("컨텐츠")
                .build();

        String json = objectMapper.writeValueAsString(postCreate);

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andDo(print());
        //when

        //Then
    }
    @Test
    public void test2() throws Exception {
        //given
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": null,\"content\": \"내용입니다.\"}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 에러입니다."))
                .andDo(print());
        //when

        //Then
    }
    
    @Test
    public void controllerPost() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다\",\"content\": \"내용입니다.\"}")
                )
                .andExpect(status().isOk())
                .andDo(print());
        assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("get 테스트")
    public void getTestValid() throws Exception{
        //given
        Post post = Post.builder()
                .title("12345678901234")
                .content("content")
                .build();
        postRepository.save(post);
        //when
        mockMvc.perform(get("/posts/{postId}",post.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("1234567890"))
                .andExpect(jsonPath("$.content").value(post.getContent()))
                .andDo(print());
        //Then
    }

    @Test
    @DisplayName("/posts 여러 개 조회")
    public void getList() throws Exception{
        //given
        Post post1 = Post.builder()
                .title("12345678901234")
                .content("content")
                .build();
        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("asdfgbnmhh")
                .content("content")
                .build();
        postRepository.save(post2);
        mockMvc.perform(get("/posts")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(post1.getId()))
                .andDo(print());
        //when

        //Then
    }
}