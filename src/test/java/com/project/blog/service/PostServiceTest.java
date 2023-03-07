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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @DisplayName("글 1페이지 조회")
    public void test3() {
        //given
        List<Post> requestPosts = IntStream.of(0, 30)
                .mapToObj(i -> Post.builder()
                            .title("김무건 제목" + i)
                            .content("컨텐츠" + i)
                            .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);
        //when
        List<PostResponse> posts = postService.getList(1);
        //Then
        assertEquals(2L, posts.size());
    }


}