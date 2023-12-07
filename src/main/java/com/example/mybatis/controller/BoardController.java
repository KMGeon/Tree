package com.example.mybatis.controller;

import com.example.mybatis.domain.Board;
import com.example.mybatis.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String test(Model model) {
        List<Board> list = boardService.getList();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public String insert() {
        return "insert";
    }

    @PostMapping("/boardInsert")
    public String insertBoard(HttpServletRequest httpServletRequest) {
        String title = httpServletRequest.getParameter("title");
        String content = httpServletRequest.getParameter("content");
        String writer = httpServletRequest.getParameter("writer");
        log.info("{},{},{}", title,content,writer);

        Integer integer = boardService.insertBoard(title, content, writer);

        if (integer > 0) {
            return "redirect:";
        }else {
            return "redirect:insert";
        }
    }

    @GetMapping("content")
    public String pathContent(@RequestParam("id") int id, Model model) {
        Board board = boardService.boardDetail(id);
        model.addAttribute("detail", board);
        return "boardDetail";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delte(id);
        return "redirect:";
    }
}
