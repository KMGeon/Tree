package com.dev.wanted.domain.application.impl;

import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.response.TodoFindByIdResponseDto;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.domain.entity.Todo;
import com.dev.wanted.domain.repository.TodoRepository;
import com.dev.wanted.global.util.LoginUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceMockForRead {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @Test
    void getTodoById_ShouldReturnTodo() {
        // Given
        Long todoId = 1L;
        LoginUserDto loginUserDto = LoginUserDto.builder().userId(1L).build();
        Todo todo = new Todo();
        when(todoRepository.findByIdAndUserId(eq(todoId), eq(loginUserDto.getUserId()))).thenReturn(Optional.of(todo));

        // When
        TodoFindByIdResponseDto responseDto = todoService.getTodoById(todoId, loginUserDto);

        // Then
        assertNotNull(responseDto);
    }

    @Test
    void findTodoListWithCondition_ShouldReturnTodoPage() {
        // Given
        int page = 0;
        int size = 10;
        TodoSearchCondition condition = new TodoSearchCondition();
        LoginUserDto loginUserDto = LoginUserDto.builder().userId(1L).build();
        Page<TodoPageWithCondition> todoPage = new PageImpl<>(Collections.emptyList());
        when(todoRepository.findTodoListWithCondition(any(Pageable.class), eq(condition), eq(loginUserDto)))
                .thenReturn(todoPage);

        // When
        Page<TodoPageWithCondition> result = todoService.findTodoListWithCondition(page, size, condition, loginUserDto);

        // Then
        assertNotNull(result);
    }
}