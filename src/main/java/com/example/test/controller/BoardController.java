package com.example.test.controller;

import com.example.test.dao.BoardMapper;
import com.example.test.dto.BoardDto;
import com.example.test.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class BoardController {
    @Autowired
    BoardService boardService;


    @RequestMapping(value = "/")
    public String boardList(){
        return "main";
    }

    @RequestMapping(value = "/boardList" ,method = RequestMethod.GET)
    public @ResponseBody List<BoardDto> boardList1(){
        List<BoardDto>list = this.boardService.boardList();
        return  list;//json 데이터 형식으로 변환해서 리턴함
    }
    //객체를 응답한다.
    //@responseBodt를 사용하면 json을 보낸다ㅣ.

    @RequestMapping(value = "/boardInsert",method = RequestMethod.POST)
    public @ResponseBody int boardInsert(BoardDto boardDto){
        int result = this.boardService.insertData(boardDto);
        log.info("result = " + result);
        return result;
    }

    @RequestMapping(value = "/boardDelete" ,method = RequestMethod.GET)
    public @ResponseBody int boardDelete(@RequestParam("idx") int idx){
        int result =this.boardService.boardDelete(idx);
        return  result;
    }
}
