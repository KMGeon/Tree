package com.giggal.board.domain.post.repository.impl;

import com.giggal.board.domain.post.dto.request.PostSearchRequest;
import com.giggal.board.domain.post.dto.response.PostSearchResponse;
import com.giggal.board.domain.post.dto.response.QPostSearchResponse;
import com.giggal.board.domain.post.entity.Post;
import com.giggal.board.domain.post.repository.PostPageRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.giggal.board.domain.member.entity.QMember.member;
import static com.giggal.board.domain.post.entity.QPost.post;

public class PostPageRepositoryImpl implements PostPageRepository {

    private final JPAQueryFactory queryFactory;

    public PostPageRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<PostSearchResponse> getPostList(PostSearchRequest postSearchRequest, Pageable pageable) {

        List<PostSearchResponse> content = queryFactory
                .select(new QPostSearchResponse(
                        member.id.as("memberId"),
                        member.name.as("memberName"),
                        post.title.as("postTitle"),
                        post.content.as("postContent")
                )).from(post)
                .leftJoin(post.member, member)
                .where(
                        postTitleEq(postSearchRequest.getPostTitle()),
                        postContentEq(postSearchRequest.getPostContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.id.asc())
                .fetch();

        JPAQuery<Post> countQuery = queryFactory
                .selectFrom(post)
                .leftJoin(post.member, member)
                .where(
                        postTitleEq(postSearchRequest.getPostTitle()),
                        postContentEq(postSearchRequest.getPostContent())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public PostSearchResponse getPost(Long postId) {
        return queryFactory
                .select(new QPostSearchResponse(
                        member.id.as("memberId"),
                        member.name.as("memberName"),
                        post.title.as("postTitle"),
                        post.content.as("postContent")
                )).from(post)
                .leftJoin(post.member, member)
                .where(post.id.eq(postId))
                .fetchOne();
    }

    private BooleanExpression postContentEq(String postContent) {
        return StringUtils.hasText(postContent) ? post.content.eq(postContent) : null;
    }

    private BooleanExpression postTitleEq(String postTitle) {
        return StringUtils.hasText(postTitle) ? post.content.eq(postTitle) : null;
    }
}
