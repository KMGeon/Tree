package com.project.blog.dto;

import com.project.blog.domain.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter@Setter
@ToString
public class PostCreate {
    @NotBlank(message = "타이틀을 입력하세요")
    private String title;

    @NotBlank(message = "내용을 입력하세요")
    private String content;

    public PostCreate() {
    }

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
