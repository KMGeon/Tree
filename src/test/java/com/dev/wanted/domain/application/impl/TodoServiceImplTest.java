package com.dev.wanted.domain.application.impl;

import com.dev.wanted.domain.application.TodoService;
import com.dev.wanted.domain.dto.request.TodoCreateRequestDto;
import com.dev.wanted.domain.dto.request.UpdateTodoRequestDto;
import com.dev.wanted.domain.entity.Todo;
import com.dev.wanted.domain.entity.User;
import com.dev.wanted.domain.repository.TodoRepository;
import com.dev.wanted.domain.repository.UserRepository;
import com.dev.wanted.domain.role.enums.RoleEnum;
import com.dev.wanted.global.exception.todo.NotFoundTodoById;
import com.dev.wanted.global.exception.todo.NotMatchUserAndTodoException;
import com.dev.wanted.global.util.LoginUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class TodoServiceImplTest {

    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    private LoginUserDto loginUserDto;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .account("test1234@test.com")
                .password("test1234!")
                .build();
        userRepository.save(user);

        loginUserDto = LoginUserDto.builder()
                .userId(1L)
                .roles(List.of(RoleEnum.USER.getRoleName()))
                .build();
    }

    @Test
    @DisplayName("todo 생성")
    public void createTodo_ValidInput_ShouldCreateTodo() {
        // given
        TodoCreateRequestDto todoCreateRequestDto = createTestTodoRequestDto();

        // when
        todoService.createTodo(todoCreateRequestDto, loginUserDto);

        // then
        Todo todo = todoRepository.findById(1L)
                .orElseThrow(() -> new NotFoundTodoById(1L));

        assertThat(todo.getName()).isEqualTo("코딩테스트 연습하기");
        assertThat(todo.getDescription()).isEqualTo("DP 학습하기");
    }


    @Nested
    class UpdateAndDeleteTodo {

        private Long createdTodoId;

        @BeforeEach
        void setUp() {
            TodoCreateRequestDto todoCreateRequestDto = createTestTodoRequestDto();
            todoService.createTodo(todoCreateRequestDto, loginUserDto);
            createdTodoId = todoRepository.findById(1L)
                    .orElseThrow(() -> new NotFoundTodoById(1L))
                    .getId();
        }

        @Test
        @DisplayName("기존의 Todo 삭제")
        public void deleteTodo_ValidId_ShouldDeleteTodo() {
            // when
            todoService.deleteTodoById(createdTodoId, loginUserDto);

            // then
            assertThat(todoRepository.count()).isZero();
        }

        @Test
        @DisplayName("기존의 Todo 삭제 - 유효하지 않은 Todo Id")
        public void deleteTodo_InvalidTodoId_ShouldThrowException() {
            // then
            assertThatThrownBy(() -> todoService.deleteTodoById(2L, loginUserDto))
                    .isInstanceOf(NotMatchUserAndTodoException.class)
                    .hasMessageContaining("해당 회원이 작성한 Todo가 아닙니다.");
        }

        @Test
        @DisplayName("Todo 업데이트")
        public void updateTodo_ValidInput_ShouldUpdateTodo() {
            // given
            UpdateTodoRequestDto updateTodoRequestDto = UpdateTodoRequestDto.builder()
                    .id(createdTodoId)
                    .name("Updated Todo")
                    .description("Updated Description")
                    .build();

            // when
            todoService.updateTodoList(updateTodoRequestDto, loginUserDto);

            // then
            Todo updatedTodo = todoRepository.findById(createdTodoId)
                    .orElseThrow(() -> new NotFoundTodoById(createdTodoId));

            assertThat(updatedTodo.getName()).isEqualTo("Updated Todo");
            assertThat(updatedTodo.getDescription()).isEqualTo("Updated Description");
        }
    }

    private TodoCreateRequestDto createTestTodoRequestDto() {
        return TodoCreateRequestDto.builder()
                .name("코딩테스트 연습하기")
                .description("DP 학습하기")
                .build();
    }
}
