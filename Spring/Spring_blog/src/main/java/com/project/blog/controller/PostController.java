package com.project.blog.controller;

import com.project.blog.config.data.UserSession;
import com.project.blog.dto.request.PostCreate;
import com.project.blog.dto.request.PostEdit;
import com.project.blog.dto.response.PostResponse;
import com.project.blog.dto.response.PostSearch;
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

    @GetMapping("/foo")
    public Long foo(UserSession userSession){
        log.info(">>>:{}"+userSession.id);
        return userSession.id;
    }
    @GetMapping("/boo")
    public String boo(){
        return "userSession.name";
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody @Valid PostCreate PostCreate, @RequestHeader("authorization") String authorization) {
        if (authorization.equals("hodolman")) {
            postService.write(PostCreate);
        }
    }

    @GetMapping("/posts/{postId}")
    public PostResponse getId(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }


}
