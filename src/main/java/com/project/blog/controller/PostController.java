package com.project.blog.controller;

import com.project.blog.domain.Post;
import com.project.blog.dto.PostCreate;
import com.project.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate PostCreate){

        log.info("title={},content={}", PostCreate.getTitle(), PostCreate.getContent());
        postService.write(PostCreate);
    }

    @GetMapping("/posts/{postId}")
    public Post getId(@PathVariable Long postId){
        return postService.get(postId);
    }


}
