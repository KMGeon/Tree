package com.example.test.dto;

import lombok.Data;
@Data
public class BoardDto {
    private int idx; // 번호
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private int count; // 조회수
    // setter , getter
}

