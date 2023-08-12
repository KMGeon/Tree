package study.querydsl.HAHA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.HAHA.domain.User;

public interface UserRepositry extends JpaRepository<User,Long> {
}
