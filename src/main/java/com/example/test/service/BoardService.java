package com.example.test.service;

import com.example.test.dto.BoardDto;

import java.util.List;

public interface BoardService {
    public List<BoardDto> boardList();
    public int insertData(BoardDto boardDto);
    public int boardDelete(int idx);
}
