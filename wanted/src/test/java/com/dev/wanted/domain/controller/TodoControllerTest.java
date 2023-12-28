package com.dev.wanted.domain.controller;

import com.dev.wanted.domain.application.TodoService;
import com.dev.wanted.domain.dto.request.TodoCreateRequestDto;
import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.request.UpdateTodoRequestDto;
import com.dev.wanted.domain.dto.response.TodoFindByIdResponseDto;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.domain.role.enums.RoleEnum;
import com.dev.wanted.global.exception.todo.NotFoundTodoById;
import com.dev.wanted.global.jwt.util.JwtTokenizer;
import com.dev.wanted.global.util.LoginUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    private static String TOKEN;

    @BeforeEach
    void setUp() {

        String ACCOUNT = "test1234@test.com";

        TOKEN = jwtTokenizer.createAccessToken(1L, ACCOUNT, List.of(RoleEnum.USER.getRoleName()));


        Page<TodoPageWithCondition> Page = null;

        LoginUserDto loginUserDto = LoginUserDto.builder()
                .userId(1L)
                .roles(List.of(RoleEnum.USER.getRoleName()))
                .build();

    }

    @Test
    @DisplayName("Todo 생성하기")
    void createTodoValid() throws Exception {
        //given
        TodoCreateRequestDto todoCreateRequestDto = TodoCreateRequestDto.builder()
                .name("제목")
                .description("설명")
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN)
                                .content(objectMapper.writeValueAsString(todoCreateRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());
        //then
        //verify()
    }

    @Test
    @DisplayName("Todo 생성하기 실패 @valid Name")
    void createTodoInvalidWithName() throws Exception {
        //given
        TodoCreateRequestDto todoCreateRequestDto = TodoCreateRequestDto.builder()
                .description("설명")
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN)
                                .content(objectMapper.writeValueAsString(todoCreateRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("정규식에 적합하지 않습니다."))
                .andDo(print());
        //then
        //verify()
    }

    @Test
    @DisplayName("Todo 생성하기 실패 @valid Description")
    void createTodoInvalidWithDescription() throws Exception {
        //given
        TodoCreateRequestDto todoCreateRequestDto = TodoCreateRequestDto.builder()
                .name("제목")
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN)
                                .content(objectMapper.writeValueAsString(todoCreateRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("정규식에 적합하지 않습니다."))
                .andDo(print());
        //then
        //verify()
    }


    @Test
    @DisplayName("특정 게시글 조회")
    void getByidInfoWithValid() throws Exception {
        //given
        Long id = 1L;
        TodoFindByIdResponseDto todoFindByIdResponseDto = TodoFindByIdResponseDto.builder()
                .id(1L)
                .description("설명")
                .name("이름")
                .build();
        given(todoService.getTodoById(anyLong(), any(LoginUserDto.class)))
                .willReturn(todoFindByIdResponseDto);
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/todo/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN)
                                .content("")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        //then
        verify(todoService).getTodoById(anyLong(), any(LoginUserDto.class));
    }

    @Test
    @DisplayName("특정 게시글 조회 실패")
    void getByInfoWithInvalid() throws Exception {
        //given
        Long id = 2L;
        TodoFindByIdResponseDto todoFindByIdResponseDto = TodoFindByIdResponseDto.builder()
                .id(1L)
                .description("설명")
                .name("이름")
                .build();
        given(todoService.getTodoById(anyLong(), any(LoginUserDto.class))).willThrow(new NotFoundTodoById(id));

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/todo/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN)
                                .content("")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
        //then
    }

    @Test
    @DisplayName("업데이트 성공")
    void updateWithValid() throws Exception {
        //given
        UpdateTodoRequestDto updateTodoRequestDto = UpdateTodoRequestDto.builder()
                .id(1L)
                .description("UPDATE")
                .name("UPDATE")
                .build();
        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN)
                                .content(objectMapper.writeValueAsString(updateTodoRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        //then
        //verify()
    }

    @Test
    @DisplayName("페이징")
    void pagingWithValid() throws Exception {
        // given
        int page = 0;
        int size = 10;
        TodoSearchCondition condition = new TodoSearchCondition();
        LoginUserDto loginUserDto = LoginUserDto.builder().userId(1L).build();
        Page<TodoPageWithCondition> todoPage = new PageImpl<>(Collections.emptyList()); // Create a sample Page<TodoPageWithCondition> object
        when(todoService.findTodoListWithCondition(anyInt(),anyInt(), eq(condition), eq(loginUserDto)))
                .thenReturn(todoPage);

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN)
                                .param("page", String.valueOf(page))
                                .param("size", String.valueOf(size))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        // then
        // Add assertions to verify the behavior based on the expected outcome
    }
}