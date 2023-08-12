package study.querydsl.HAHA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.HAHA.dto.BoardSearchCondition;
import study.querydsl.HAHA.dto.BoardUserResponse;

public interface BoardRepositoryCustom {
    Page<BoardUserResponse> searchPageComplex(BoardSearchCondition condition, Pageable pageable);
}
