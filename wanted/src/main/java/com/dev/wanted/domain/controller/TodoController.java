package com.dev.wanted.domain.controller;

import com.dev.wanted.domain.application.TodoService;
import com.dev.wanted.domain.dto.request.TodoCreateRequestDto;
import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.request.UpdateTodoRequestDto;
import com.dev.wanted.domain.dto.response.TodoFindByIdResponseDto;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.global.util.IfLogin;
import com.dev.wanted.global.util.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //3 게시글 생성
    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodoList(
            @Valid @RequestBody TodoCreateRequestDto todoCreateRequestDto,
            @IfLogin LoginUserDto loginUserDto
    ) {
        todoService.createTodo(todoCreateRequestDto, loginUserDto);
    }

    //페이징 게시글 조회
    @GetMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public Page<TodoPageWithCondition> findTodoListWithCondition(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            TodoSearchCondition condition,
            @IfLogin LoginUserDto loginUserDto
    ) {
        return todoService.findTodoListWithCondition(page,size, condition, loginUserDto);
    }

    //5 특정 게시글 조회
    @GetMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoFindByIdResponseDto getTodoByIdInfo(
            @PathVariable Long id,
            @IfLogin LoginUserDto loginUserDto
    ) {
        log.info("..................{}",loginUserDto.getUserId());
        return todoService.getTodoById(id, loginUserDto);
    }

    //특정 게시글 수정
    @PutMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public void updateTodoList(
            @RequestBody UpdateTodoRequestDto updateTodoRequestDto,
            @IfLogin LoginUserDto loginUserDto
    ) {
        todoService.updateTodoList(updateTodoRequestDto, loginUserDto);
    }

    //특
    @DeleteMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoById(
            @PathVariable Long id,
            @IfLogin LoginUserDto loginUserDto
    ) {
        todoService.deleteTodoById(id, loginUserDto);
    }

}
