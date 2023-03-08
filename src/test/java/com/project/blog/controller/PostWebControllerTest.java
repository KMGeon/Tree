package com.project.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.dto.PostCreate;
import com.project.blog.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PostWebControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("fds")
    public void fs() throws Exception{
        //given
        PostCreate postCreate = PostCreate.builder()
                .content("co123ntent")
                .title("ti123tle")
                .build();
        String JSON = objectMapper.writeValueAsString(postCreate);
        doNothing().when(postService).write(postCreate);
        //when
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        //Then
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"id");
//        assertThat(postService.getList(pageable)).isNull();
    }

}