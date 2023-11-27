package com.giggal.board.domain.post.repository;

import com.giggal.board.domain.post.dto.request.PostSearchRequest;
import com.giggal.board.domain.post.dto.response.PostSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostPageRepository {
    Page<PostSearchResponse> getPostList(PostSearchRequest postSearchRequest, Pageable pageable);

    PostSearchResponse getPost(Long postId);
}
