package com.dev.wanted.domain.aop;

import com.dev.wanted.domain.dto.request.UpdateTodoRequestDto;
import com.dev.wanted.domain.repository.TodoRepository;
import com.dev.wanted.global.exception.todo.NotMatchUserAndTodoException;
import com.dev.wanted.global.util.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TodoAuthorityCheckAop {

    private final TodoRepository todoRepository;

    public TodoAuthorityCheckAop(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Around("@annotation(todoAuthCheck)")
    public Object checkTodoAuthority(ProceedingJoinPoint joinPoint, TodoAuthCheck todoAuthCheck) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (args.length > 1 && args[1] instanceof LoginUserDto) {
            long id;
            if (args[0] instanceof Long) {
                id = (Long) args[0];
                log.info("Long Id :{}", id);
            } else if (args[0] instanceof UpdateTodoRequestDto) {
                id = ((UpdateTodoRequestDto) args[0]).getId();
                log.info("UpdateTodoRequestDto:{}", id);
            } else {
                throw new IllegalArgumentException("잘못된 메소드 인자입니다.");
            }

            LoginUserDto loginUserDto = (LoginUserDto) args[1];
            log.info("LoginUserDto:{}", loginUserDto.getUserId());

            if (todoRepository.findByIdAndUserId(id, loginUserDto.getUserId()).isEmpty()) {
                log.info("권한 체크 error 발생");
                throw new NotMatchUserAndTodoException(loginUserDto.getUserId());
            }
        }
        return joinPoint.proceed();
    }
}