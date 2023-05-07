package com.giggal.board.domain.post.controller;

import com.giggal.board.domain.post.application.PostService;
import com.giggal.board.domain.post.dto.request.PostCreateRequest;
import com.giggal.board.domain.post.dto.request.PostSearchRequest;
import com.giggal.board.domain.post.dto.request.PostUpdateRequest;
import com.giggal.board.domain.post.dto.response.PostSearchResponse;
import com.giggal.board.global.util.IfLogin;
import com.giggal.board.global.util.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
            @RequestBody PostCreateRequest postCreateRequest,
            @IfLogin LoginUserDto userDto
    ) {
        log.info(String.format("postCreateRequest:>>{%s}", postCreateRequest));
        log.info(String.format("userDto:>>{%s}", userDto));
        postService.writePost(postCreateRequest, userDto);
    }

    @GetMapping("posts")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostSearchResponse> findPostListWithEdit(
            PostSearchRequest request,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "3", required = false) int size
    ) {
        return postService.getPostListWithSearch(request, page, size);
    }

    @GetMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostSearchResponse detailPost(
            @PathVariable Long postId
    ) {
        return postService.detailPost(postId);
    }

    @PatchMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest postUpdateRequest
    ) {
        postService.editPost(postUpdateRequest, postId);
    }

    @DeleteMapping("{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable Long postId
    ) {
        postService.deletePost(postId);
    }

}
