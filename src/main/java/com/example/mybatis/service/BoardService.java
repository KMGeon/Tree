package com.example.mybatis.service;

import com.example.mybatis.domain.Board;
import com.example.mybatis.mapper.BoardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Slf4j
@Service
public class BoardService {

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public List<Board>getList() {
        return boardDao.getList();
    }

    public Integer insertBoard(String title, String content, String writer) {
        int result = boardDao.insertBoard(title, content, writer);
        log.info(">>> int {} ", result);
        return result;
    }

    public Board boardDetail(int id) {
        Board detail = boardDao.detail(id);

        return detail;
    }

    public int delte(Long id) {
        boardDao.delete(id);
        return 0;
    }
}
