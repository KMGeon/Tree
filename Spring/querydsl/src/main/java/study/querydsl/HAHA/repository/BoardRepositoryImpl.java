package study.querydsl.HAHA.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import study.querydsl.HAHA.domain.Board;
import study.querydsl.HAHA.dto.BoardSearchCondition;
import study.querydsl.HAHA.dto.BoardUserResponse;
import study.querydsl.HAHA.dto.QBoardUserResponse;

import java.util.List;

import static study.querydsl.HAHA.domain.QBoard.board;
import static study.querydsl.HAHA.domain.QUser.user;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    public BoardRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<BoardUserResponse> searchPageComplex(BoardSearchCondition condition, Pageable pageable) {
        List<BoardUserResponse> content = queryFactory
                .select(new QBoardUserResponse(
                        user.name,
                        user.age,
                        board.name
                )).from(board)
                .leftJoin(board.user, user)
                .where(
                        userNameEq(condition.getUsername()),
                        boardNameEq(condition.getBoardName()),
                        ageGoeEq(condition.getAgeGoe()),
                        ageLoeEq(condition.getAgeLoe())
                ).orderBy(board.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        JPAQuery<Board> countQuery = queryFactory
                .select(board)
                .from(board)
                .leftJoin(board.user, user)
                .where(
                        userNameEq(condition.getUsername()),
                        boardNameEq(condition.getBoardName()),
                        ageGoeEq(condition.getAgeGoe()),
                        ageLoeEq(condition.getAgeLoe())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression ageGoeEq(Integer ageGoe) {
        return ageGoe != null ? user.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoeEq(Integer ageLoe) {
        return ageLoe != null ? user.age.loe(ageLoe) : null;
    }

    private BooleanExpression boardNameEq(String boardName) {
        return StringUtils.hasText(boardName) ? board.name.eq(boardName) : null;
    }

    private BooleanExpression userNameEq(String username) {
        return StringUtils.hasText(username) ? user.name.eq(username) : null;
    }
}
