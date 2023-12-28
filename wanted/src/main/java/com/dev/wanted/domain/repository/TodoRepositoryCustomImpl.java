package com.dev.wanted.domain.repository;

import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.response.QTodoPageWithCondition;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.domain.entity.Todo;
import com.dev.wanted.global.exception.user.NotFoundUserId;
import com.dev.wanted.global.util.LoginUserDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.dev.wanted.domain.entity.QTodo.todo;
import static com.dev.wanted.domain.entity.QUser.user;


public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public TodoRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<TodoPageWithCondition> findTodoListWithCondition(Pageable pageable, TodoSearchCondition condition, LoginUserDto loginUserDto) {
        List<TodoPageWithCondition> content = queryFactory.select(
                        new QTodoPageWithCondition(
                                todo.id,
                                todo.name,
                                todo.description
                        ))
                .from(todo)
                .join(todo.user, user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Todo> countQuery = queryFactory
                .selectFrom(todo);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

}
