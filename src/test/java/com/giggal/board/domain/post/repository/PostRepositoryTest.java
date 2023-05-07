package com.giggal.board.domain.post.repository;

import com.giggal.board.domain.enums.TestEnum;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.post.dto.request.PostSearchRequest;
import com.giggal.board.domain.post.dto.response.PostSearchResponse;
import com.giggal.board.domain.post.entity.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@ActiveProfiles({"local"})
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Nested
    class page {
        @BeforeEach
        void setUp() {
            IntStream.rangeClosed(1, 100)
                    .forEach(i -> {

                        Member member = Member.builder()
                                .name(TestEnum.NAME.getName() + i)
                                .build();

                        Post post = Post.builder()
                                .title("제목" + i)
                                .content("내용" + i)
                                .build();

                        post.changeMemberWithPost(member);

                        postRepository.save(post);
                    });
        }

        @Test
        @DisplayName("QueryDSL 페이징 처리")
        public void pageWithValid() throws Exception {
            //given
            PostSearchRequest postSearchRequest = PostSearchRequest.builder().build();
            PageRequest pageRequest = PageRequest.of(0, 3);
            //when
            Page<PostSearchResponse> result = postRepository.getPostList(postSearchRequest, pageRequest);

            //Then
            assertThat(result).extracting("memberName")
                    .containsExactly(
                            "김무건1",
                            "김무건2",
                            "김무건3"
                    );

            assertThat(result.getSize()).isEqualTo(3);
        }

        @Test
        @DisplayName("아이디를 통한 단건 데이터 조회 left Join")
        public void findResourceWithValidPostId() throws Exception {
            //given
            //when
            PostSearchResponse post = postRepository.getPost(1L);
            //Then
            assertThat(post.getPostTitle()).isEqualTo("제목1");
            assertThat(post.getPostContent()).isEqualTo("내용1");
            assertThat(post.getMemberName()).isEqualTo("김무건1");
        }
    }

}