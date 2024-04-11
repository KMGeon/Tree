package com.example.mybatis.controller;

import com.example.mybatis.application.BoardService;
import com.example.mybatis.controller.dto.request.InsertBoardRequest;
import com.example.mybatis.controller.dto.response.BoardInfoResponseDTO;
import com.example.mybatis.controller.validGroup.ValidationSequence;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/board")
    public ResponseEntity<List<BoardInfoResponseDTO>> getBoardList() {
        logger.info("====== /board [" + getClass().getSimpleName() + "getBoardList()] start ======");
        List<BoardInfoResponseDTO> boardInfosResponse = boardService.getBoardInfos();
        logger.info("====== /board [" + getClass().getSimpleName() + "getBoardList()] start ======");
        return ResponseEntity.ok(boardInfosResponse);
    }

    @PostMapping("/board")
    public Integer insertBoard(
            @Validated(ValidationSequence.class)
            @RequestBody InsertBoardRequest request
    ) {
        logger.info("====== " + getClass().getSimpleName() + "insertBoard()] start ======");
        Integer result = boardService.insertBoard(request);
        logger.info("====== " + getClass().getSimpleName() + "insertBoard()] insertResult :", result);
        logger.info("====== " + getClass().getSimpleName() + "insertBoard()] end ======");
        return result;
    }
}
