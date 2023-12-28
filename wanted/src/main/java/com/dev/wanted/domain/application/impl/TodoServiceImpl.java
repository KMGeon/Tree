package com.dev.wanted.domain.application.impl;

import com.dev.wanted.domain.aop.TodoAuthCheck;
import com.dev.wanted.domain.application.TodoService;
import com.dev.wanted.domain.dto.request.TodoCreateRequestDto;
import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.request.UpdateTodoRequestDto;
import com.dev.wanted.domain.dto.response.TodoFindByIdResponseDto;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.domain.entity.Todo;
import com.dev.wanted.domain.entity.User;
import com.dev.wanted.domain.repository.TodoRepository;
import com.dev.wanted.domain.repository.UserRepository;
import com.dev.wanted.global.exception.todo.NotFoundTodoById;
import com.dev.wanted.global.util.LoginUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(
            TodoRepository todoRepository,
            UserRepository userRepository
    ) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void createTodo(TodoCreateRequestDto todoCreateRequestDto, LoginUserDto loginUserDto) {
        User user = userRepository.findById(loginUserDto.getUserId())
                .orElseThrow(() -> new NotFoundTodoById(loginUserDto.getUserId()));

        todoRepository.save(Todo.builder()
                .name(todoCreateRequestDto.getName())
                .description(todoCreateRequestDto.getDescription())
                .user(user)
                .build());
    }

    @Override
    @Transactional
    @TodoAuthCheck
    public void deleteTodoById(Long id, LoginUserDto loginUserDto) {
        todoRepository.deleteById(id);
    }

    @Override
    @Transactional
    @TodoAuthCheck
    public void updateTodoList(UpdateTodoRequestDto updateTodoRequestDto, LoginUserDto loginUserDto) {
        todoRepository.findById(updateTodoRequestDto.getId())
                .orElseThrow(() -> new NotFoundTodoById(updateTodoRequestDto.getId()))
                .updateTodo(updateTodoRequestDto);
    }

    @Override
    @Transactional(readOnly = true)
    public TodoFindByIdResponseDto getTodoById(Long id, LoginUserDto loginUserDto) {
        return TodoFindByIdResponseDto.of(todoRepository.findByIdAndUserId(id,loginUserDto.getUserId())
                .orElseThrow(() -> new NotFoundTodoById(id)));
    }

    @Override
    @Transactional
    public Page<TodoPageWithCondition> findTodoListWithCondition(int page, int size, TodoSearchCondition condition, LoginUserDto loginUserDto) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findTodoListWithCondition(pageable, condition, loginUserDto);
    }

}
