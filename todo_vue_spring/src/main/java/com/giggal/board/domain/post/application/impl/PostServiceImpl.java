package com.giggal.board.domain.post.application.impl;

import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.domain.post.application.PostService;
import com.giggal.board.domain.post.dto.request.PostCreateRequest;
import com.giggal.board.domain.post.dto.request.PostSearchRequest;
import com.giggal.board.domain.post.dto.request.PostUpdateRequest;
import com.giggal.board.domain.post.dto.response.PostSearchResponse;
import com.giggal.board.domain.post.entity.Post;
import com.giggal.board.domain.post.enums.BadTitle;
import com.giggal.board.domain.post.repository.PostRepository;
import com.giggal.board.global.exception.member.NotFoundMemberId;
import com.giggal.board.global.exception.post.FindBadTitle;
import com.giggal.board.global.exception.post.NotFoundPostWithId;
import com.giggal.board.global.util.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostServiceImpl(
            PostRepository postRepository,
            MemberRepository memberRepository
    ) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public void writePost(
            PostCreateRequest postCreateRequest,
            LoginUserDto userDto
    ) {
        Member member = memberRepository.findById(userDto.getMemberId())
                .orElseThrow(() -> new NotFoundMemberId(userDto.getMemberId()));
        log.info(String.format("memberId:>>{%s}", userDto.getMemberId()));


        String title = postCreateRequest.getTitle();
        log.info(String.format("title:>>{%s}", title));

        findTitleWithBadTile(title);

        Post post = Post.builder()
                .title(title)
                .content(postCreateRequest.getContent())
                .build();

        post.changeMemberWithPost(member);


        postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostSearchResponse> getPostListWithSearch(PostSearchRequest request, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.getPostList(request, pageable);
    }

    @Override
    @Transactional
    public void editPost(PostUpdateRequest request, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundPostWithId(postId));

        post.updatePost(request);
    }

    @Override
    @Transactional(readOnly = true)
    public PostSearchResponse detailPost(Long postId) {
        return postRepository.getPost(postId);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundPostWithId(postId));

        postRepository.delete(post);
    }

    @Override
    public List<Post> findDiscord() {

        return postRepository.findAll();
    }

    private void findTitleWithBadTile(String title) {

        List<String> badWords = Arrays.asList(
                BadTitle.dog.getTitleName(),
                BadTitle.ssibal.getTitleName(),
                BadTitle.joy.getTitleName(),
                BadTitle.reload.getTitleName()
        );

        badWords.stream().filter(title::contains).forEachOrdered(word -> {
            throw new FindBadTitle(title);
        });
    }
}
