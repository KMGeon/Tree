package study.querydsl.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.study.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> ,MemberRepositoryCustom{
    List<Member>findByUsername(String username);
}
