package com.example.hellospring.controller;

import com.example.hellospring.dto.PostsResponseDto;
import com.example.hellospring.dto.PostsUpdateRequestDto;
import com.example.hellospring.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String helloIndex(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }


    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id , @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id , Model model){
        model.addAttribute("post", postsService.findById(id));
        return "posts-update";
    }


    @GetMapping("/posts/save")
    public String postSaves(){
        return "posts-save";
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
