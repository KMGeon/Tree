package com.giggal.board.domain.post.entity;

import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.post.dto.request.PostUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public void updatePost(PostUpdateRequest postUpdateRequest) {
        this.title = postUpdateRequest.getTitle();
        this.content = postUpdateRequest.getContent();
    }

    public void changeMemberWithPost(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
