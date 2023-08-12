package com.example.hellospring.service;

import com.example.hellospring.domain.posts.Posts;
import com.example.hellospring.domain.posts.PostsRepository;
import com.example.hellospring.dto.request.PostSaveRequstDto;
import com.example.hellospring.dto.request.PostsUpdateRequestDto;
import com.example.hellospring.dto.response.PostsListResponseDto;
import com.example.hellospring.dto.response.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;


    @Transactional
    public Long save(PostSaveRequstDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = getId(id);

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    private Posts getId(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return posts;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = getId(id);

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = getId(id);

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
