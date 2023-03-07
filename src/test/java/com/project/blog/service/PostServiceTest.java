package com.project.blog.service;

import com.project.blog.domain.Post;
import com.project.blog.dto.PostCreate;
import com.project.blog.dto.PostResponse;
import com.project.blog.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("글 작성")
    public void writeValid() throws Exception {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("타이틀")
                .content("내용 입니다.")
                .build();

        //when
        postService.write(postCreate);
        //Then
        Assertions.assertEquals(1L, postRepository.count());
    }

    @Test
    @DisplayName("설명")
    public void test() throws Exception {
        //given
        Post rePost = Post.builder()
                .title("foo")
                .content("content")
                .build();

        postRepository.save(rePost);
        PostResponse post = postService.get(rePost.getId());
        assertThat(post).isNotNull();
    }

    @Test
    @DisplayName("글 1개만 조회")
    public void getOnePost() throws Exception {
        //given
        Post requPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();

        postRepository.save(requPost);
        //when
        PostResponse post = postService.get(requPost.getId());

        //Then
        assertNotNull(post);

        assertEquals(1L, postRepository.count());
        assertEquals("foo", post.getTitle());

    }

    @Test
    @DisplayName("글 여러개 조회/post")
    public void test3() throws Exception {
        //given

        postRepository.saveAll(List.of(Post.builder()
                .title("foo1")
                .content("bar1")
                .build(), Post.builder()
                        .title("foo2")
                        .content("bar2")
                        .build()));
        //when
        List<PostResponse> posts = postService.getList();

        //Then
        assertEquals(2L, posts.size());
    }
}