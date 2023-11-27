package com.giggal.board.domain.post.repository;

import com.giggal.board.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostPageRepository {
}
