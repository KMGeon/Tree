package com.dev.wanted.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoPageWithCondition {
    private Long id;
    private String name;
    private String description;

    @QueryProjection
    public TodoPageWithCondition(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
