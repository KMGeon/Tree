package com.example.mybatis.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    Long id;
    String title;
    String content;
    String writer;
    int count;
}
