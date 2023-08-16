package com.dev.wanted.domain.repository;

import com.dev.wanted.domain.dto.request.TodoSearchCondition;
import com.dev.wanted.domain.dto.response.QTodoPageWithCondition;
import com.dev.wanted.domain.dto.response.TodoPageWithCondition;
import com.dev.wanted.global.exception.user.NotFoundUserId;
import com.dev.wanted.global.util.LoginUserDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
                        new QTodoPageWithCondition(todo.id, todo.name))
                .from(todo)
                .join(todo.user, user)
                .where(
                        todoCreatedByUser(loginUserDto.getUserId())
                )
                .limit(pageable.getPageSize())
                .fetch();

        int totalSize = queryFactory
                .selectFrom(todo)
                .where(
                        todoCreatedByUser(loginUserDto.getUserId())
                ).fetch().size();


        return new PageImpl<>(content, pageable, totalSize);
    }

    private BooleanExpression todoCreatedByUser(Long userId) {
        if (userId == null) {
            throw new NotFoundUserId(null);
        }
        return todo.user.id.eq(userId);
    }


}
