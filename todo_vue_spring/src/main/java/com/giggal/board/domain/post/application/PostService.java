package com.giggal.board.domain.post.application;

import com.giggal.board.domain.post.dto.request.PostCreateRequest;
import com.giggal.board.domain.post.dto.request.PostSearchRequest;
import com.giggal.board.domain.post.dto.request.PostUpdateRequest;
import com.giggal.board.domain.post.dto.response.PostSearchResponse;
import com.giggal.board.domain.post.entity.Post;
import com.giggal.board.global.util.LoginUserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    void writePost(PostCreateRequest postCreateRequest, LoginUserDto userDto);

    Page<PostSearchResponse> getPostListWithSearch(PostSearchRequest request, int page, int size);

    void editPost(PostUpdateRequest request, Long postId);

    PostSearchResponse detailPost(Long postId);

    void deletePost(Long postId);

    List<Post> findDiscord();
}
