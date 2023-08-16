package com.dev.wanted.domain.repository;

import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.global.util.LoginUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TodoRepositoryCustom {
    Page<TodoPageWithCondition> findTodoListWithCondition(Pageable pageable, TodoSearchCondition condition, LoginUserDto loginUserDto);
}
