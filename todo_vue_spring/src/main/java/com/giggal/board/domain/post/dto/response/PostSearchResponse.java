package com.giggal.board.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PostSearchResponse {

    private Long memberId;
    private String memberName;
    private String postTitle;
    private String postContent;

    @QueryProjection
    public PostSearchResponse(
            Long memberId,
            String memberName,
            String postTitle,
            String postContent
    ) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
