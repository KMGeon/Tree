package com.study.jenkinssession.controller;

import com.study.jenkinssession.BoardRepository;
import com.study.jenkinssession.domain.Board;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Board>> insertBoard() {
        List<Board> all = boardRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
