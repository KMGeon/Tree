package com.project.blog.service;

import com.project.blog.domain.Post;
import com.project.blog.dto.request.PostCreate;
import com.project.blog.dto.request.PostEdit;
import com.project.blog.dto.response.PostResponse;
import com.project.blog.dto.response.PostSearch;
import com.project.blog.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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

    @Autowired
    EntityManager entityManager;


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
    @DisplayName("글 여러개 조회")
    void test3() {
        // given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("title" + i)
                        .content("content" + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);


        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        // when
        List<PostResponse> posts = postService.getList(postSearch);

        // then
        assertEquals(10L , posts.size());
        assertEquals("title19" , posts.get(0).getTitle());

    }

    @Test
    @DisplayName("patch/posts{id}{edit} 수정 조회 -> 글 제목 수정")
    public void test4() throws Exception{
        //given
        Post post = Post.builder()
                .title("김무건")
                .content("제목")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("건무김")
                .content("제목")
                .build();
        //when
        postService.edit(post.getId(),postEdit);
        //Then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("수정을 하던 도중에 오류가 생겼습니다." + post.getId()));

        assertThat(changePost.getTitle()).isEqualTo("건무김");
        assertThat(changePost.getContent()).isEqualTo("제목");
    }

    @Test
    @DisplayName("게시글 삭제 - 존재하지 않는 글")
    void test8() {
        // given
        Post post = Post.builder()
                .title("김무건")
                .content("content")
                .build();
        postRepository.save(post);

        postService.delete(post.getId());

        // expected
        assertThat(postRepository.count()).isEqualTo(0);
    }


}