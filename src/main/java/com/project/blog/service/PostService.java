package com.project.blog.service;

import com.project.blog.domain.Post;
import com.project.blog.domain.PostEditor;
import com.project.blog.dto.request.PostCreate;
import com.project.blog.dto.request.PostEdit;
import com.project.blog.dto.PostResponse;
import com.project.blog.dto.PostSearch;
import com.project.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public void write(PostCreate postCreate) {
        postRepository.save(Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build());
    }

    @Transactional(readOnly = true)
    public PostResponse get(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        PostResponse response = PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        return response;
    }
    @Transactional(readOnly = true)
    public List<PostResponse> getList(PostSearch postSearch) {
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("오류"));

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        postRepository.delete(post);
    }
}
