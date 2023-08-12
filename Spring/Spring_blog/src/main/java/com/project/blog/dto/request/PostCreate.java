package com.project.blog.dto.request;

import com.project.blog.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    public Post toEntity(Post post) {
        return Post.builder()
                .title(post.getTitle())
                .content(post.getContent()).build();
    }

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
