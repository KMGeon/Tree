package com.dev.wanted.domain.repository;

import com.dev.wanted.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {
    Optional<Todo> findByIdAndUserId(Long id, Long userId);
}
