package com.project.blog.service;

import com.project.blog.domain.Post;
import com.project.blog.dto.PostCreate;
import com.project.blog.repository.PostRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("글 작성")
    public void writeValid() throws Exception{
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("타이틀")
                .content("내용 입니다.")
                .build();

        //when
        postService.write(postCreate);
        //Then
        Assertions.assertEquals(1L ,postRepository.count());
    }

    @Test
    @DisplayName("설명")        
    public void test() throws Exception{
        //given
        Post rePost = Post.builder()
                .title("foo")
                .content("content")
                .build();

        postRepository.save(rePost);

        Post post = postService.get(rePost.getId());

        //when
        AssertionsForClassTypes.assertThat(post).isNotNull();


        //Then
    }
}