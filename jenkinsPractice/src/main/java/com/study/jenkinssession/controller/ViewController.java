package com.study.jenkinssession.controller;

import com.study.jenkinssession.BoardRepository;
import com.study.jenkinssession.domain.Board;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final BoardRepository boardRepository;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("insert")
    public String insert() {
        return "insert";
    }


    @PostMapping("/boardInsert")
    public String insertBoard(HttpServletRequest httpServletRequest) {
        String title = httpServletRequest.getParameter("title");
        String content = httpServletRequest.getParameter("content");
        String writer = httpServletRequest.getParameter("writer");

        Board board = Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();

        boardRepository.save(board);
        return "redirect:/";
    }
}
