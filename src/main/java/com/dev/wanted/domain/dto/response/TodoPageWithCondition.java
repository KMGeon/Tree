package com.dev.wanted.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoPageWithCondition {
    private Long id;
    private String name;

    @QueryProjection
    public TodoPageWithCondition(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
