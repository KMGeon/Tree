package com.example.mybatis.controller;

import com.example.mybatis.domain.Board;
import com.example.mybatis.dto.BoardResponseDto;
import com.example.mybatis.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String test(Model model) {
        List<BoardResponseDto> list = boardService.getList();
        model.addAttribute("list", list);
        return "index";
    }
}
