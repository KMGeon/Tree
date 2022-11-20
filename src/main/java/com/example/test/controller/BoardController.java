package com.example.test.controller;

import com.example.test.dao.BoardMapper;
import com.example.test.dto.BoardDto;
import com.example.test.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/boardDelete" ,method = RequestMethod.POST)
    public @ResponseBody int boardDelete(@RequestParam("idx") int idx){
        int result =this.boardService.boardDelete(idx);
        return  result;
    }

@PostMapping("/updatePost")
    public @ResponseBody int boardUpdatePost (@RequestParam("idx")int idx,@RequestParam("title")String title, @RequestParam("content") String content,BoardDto boardDto){
        int result= this.boardService.boardUpdate(boardDto);
//        model.addAttribute("idx",boardDto.getIdx());
//        model.addAttribute("title",boardDto.getTitle());
//        model.addAttribute("content",boardDto.getContent());
        // let param = {"idx":idx, "title":title,"content":
        log.info("result"+result);
        return result;
    }

}
