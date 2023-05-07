package com.giggal.board.domain.post.application.impl;

import com.giggal.board.domain.enums.TestEnum;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.domain.post.application.PostService;
import com.giggal.board.domain.post.dto.request.PostCreateRequest;
import com.giggal.board.domain.post.dto.request.PostUpdateRequest;
import com.giggal.board.domain.post.dto.response.PostSearchResponse;
import com.giggal.board.domain.post.entity.Post;
import com.giggal.board.domain.post.enums.BadTitle;
import com.giggal.board.domain.post.repository.PostRepository;
import com.giggal.board.global.exception.member.NotFoundMemberId;
import com.giggal.board.global.exception.post.FindBadTitle;
import com.giggal.board.global.exception.post.NotFoundPostWithId;
import com.giggal.board.global.util.LoginUserDto;
import com.giggal.board.common.GeoReader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@SpringBootTest
@ActiveProfiles({"local"})
class PostServiceImplTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    MemberRepository memberRepository;

    private GeoReader geoIp2Mock;

    @BeforeEach
    void setUp() throws IOException {

        geoIp2Mock = new GeoReader();

        Member member = Member.builder()
                .email(TestEnum.VALID_EMAIL.getName())
                .password(TestEnum.VALID_PASSWORD.getName())
                .name(TestEnum.NAME.getName())
                .build();

        memberRepository.save(member);
    }



    @Test
    @DisplayName("게시글 작성")
    public void writePostWithValidRequest() throws Exception{
        //given
        PostCreateRequest postCreateRequest =PostCreateRequest.builder()
                .title("게시글 제목")
                .content("테스트 코드 작성하고 있습니다.")
                .build();

        LoginUserDto userDto =LoginUserDto.builder()
                .memberId(1L)
                .build();
        //when
        postService.writePost(postCreateRequest,userDto);
        //Then
        Assertions.assertThat(postRepository.count()).isNotNull();
        Assertions.assertThat(postRepository.findById(1L)).isNotNull();
    }

    @Test
    @DisplayName("게시글 작성 NotFoundMemberId 예외")
    public void writePostWithInvalidMemberId(){
        //given
        PostCreateRequest postCreateRequest =PostCreateRequest.builder()
                .title("게시글 제목")
                .content("테스트 코드 작성하고 있습니다.")
                .build();

        LoginUserDto userDto =LoginUserDto.builder()
                .memberId(99L)
                .build();
        //Then
            Assertions.assertThatThrownBy(() -> postService.writePost(
                    postCreateRequest,
                            userDto
                    ))
                    .isInstanceOf(NotFoundMemberId.class)
                    .hasMessage("Not Found Member With:99");
    }

    @Test
    @DisplayName("비속어에 대한 예외를 처리")
    public void findTitleWithBadTitle(){
        //given
        PostCreateRequest postCreateRequest =PostCreateRequest.builder()
                .title(BadTitle.dog.getTitleName())
                .content("테스트 코드 작성하고 있습니다.")
                .build();

        LoginUserDto userDto =LoginUserDto.builder()
                .memberId(1L)
                .build();
        //Then
        Assertions.assertThatThrownBy(() -> postService.writePost(
                        postCreateRequest,
                        userDto
                ))
                .isInstanceOf(FindBadTitle.class)
                .hasMessage("don`t write bad title나쁜말1");
    }

    @Nested
    class detailPost{
        @BeforeEach
        void setUp(){
            Post post= Post.builder()
                    .title("제목1")
                    .content("내용1")
                    .build();

            postRepository.save(post);
        }

        @Test
        @DisplayName("단일 게시글 검색")
        public void findPostDetailWithPostId() throws Exception{
            //given

            //when
            PostSearchResponse response = postService.detailPost(1L);
            //Then
            Assertions.assertThat(response.getPostTitle()).isEqualTo("제목1");
            Assertions.assertThat(response.getPostContent()).isEqualTo("내용1");
        }

        @Test
        @DisplayName("단일 게시글 수정")
        public void editPostWithValidRequest() throws Exception{
            //given
            PostUpdateRequest postUpdateRequest =PostUpdateRequest.builder()
                    .title("변경된 제목")
                    .content("변경된 내용")
                    .build();

            Long postId = 1L;
            //when
            postService.editPost(postUpdateRequest,postId);
            //Then
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundPostWithId(postId));

            Assertions.assertThat(post.getTitle()).isEqualTo("변경된 제목");
            Assertions.assertThat(post.getContent()).isEqualTo("변경된 내용");
        }

        @Test
        @DisplayName("단일 게시글 삭제")
        public void deletePostWithPostId() throws Exception{
            //given
            //when
            postService.deletePost(1L);
            //Then
            Assertions.assertThat(postRepository.count()).isNotNull();
        }
    }

}