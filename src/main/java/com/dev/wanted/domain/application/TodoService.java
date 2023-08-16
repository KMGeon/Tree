package com.dev.wanted.domain.application;

import com.dev.wanted.domain.dto.request.TodoCreateRequestDto;
import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.request.UpdateTodoRequestDto;
import com.dev.wanted.domain.dto.response.TodoFindByIdResponseDto;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.global.util.LoginUserDto;
import org.springframework.data.domain.Page;

public interface TodoService {
    void createTodo(TodoCreateRequestDto todoCreateRequestDto, LoginUserDto loginUserDto);

    void deleteTodoById(Long id, LoginUserDto loginUserDto);

    void updateTodoList(UpdateTodoRequestDto updateTodoRequestDto, LoginUserDto loginUserDto);

    TodoFindByIdResponseDto getTodoById(Long id, LoginUserDto loginUserDto);

    Page<TodoPageWithCondition> findTodoListWithCondition(int page, int size, TodoSearchCondition condition, LoginUserDto loginUserDto);
}
