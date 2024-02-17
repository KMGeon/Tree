package com.example.mybatis.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int count;

    @Builder
    public Board(Long id, String title, String content, String writer, int count) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.count = count;
    }
}

