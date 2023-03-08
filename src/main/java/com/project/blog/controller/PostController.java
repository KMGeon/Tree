package com.project.blog.controller;

import com.project.blog.dto.PostCreate;
import com.project.blog.dto.PostEdit;
import com.project.blog.dto.PostResponse;
import com.project.blog.dto.PostSearch;
import com.project.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody @Valid PostCreate PostCreate){
        postService.write(PostCreate);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse getId(@PathVariable Long postId){
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch){
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId , @RequestBody @Valid PostEdit postEdit){
        postService.edit(postId , postEdit);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }


}
