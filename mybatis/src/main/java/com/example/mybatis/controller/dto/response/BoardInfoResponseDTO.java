package com.example.mybatis.controller.dto.response;

import com.example.mybatis.domain.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardInfoResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int count;
    public static BoardInfoResponseDTO from(Board board) {
        BoardInfoResponseDTO dto = new BoardInfoResponseDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setWriter(board.getWriter());
        dto.setCount(board.getCount());
        return dto;
    }
}