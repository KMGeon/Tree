package com.example.mybatis.service;

import com.example.mybatis.domain.Board;
import com.example.mybatis.dto.BoardResponseDto;
import com.example.mybatis.mapper.BoardDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public List<BoardResponseDto>getList() {
        return boardDao.getList();
    }
}
