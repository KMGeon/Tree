package study.querydsl.HAHA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.HAHA.domain.Board;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardRepositoryCustom {
}
