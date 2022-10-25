package kr.or.ddit.Controller;

import kr.or.ddit.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/board")
@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BookVO bookVO;

    @GetMapping("/register")
    public void registerForm() {
        log.info("registerForm");
    }

    // 속성이 하나일 때는 속성명을 생략할 수 있다.
    @RequestMapping("/modify")
    public void modifyForm() {
        log.info("modifyForm");
    }

    // 2.경로 패턴 매핑 : 요청 경로를 동적이로 표현 가능 / (url경로 상의 변하는 값을 경로 변수 취급)
    // ex ) 요청 uri : http://localhost:8080/board/read/100 =>게시판 100번째 글
    // http://localhost:8080/board/read/101 =>게시판 101번째 글
    // 보통 옛날에는 http://localhost:8080/board/read?boardId=101 => 쿼리파라미터를 사용

    @RequestMapping("/read/{boardNo}")
    public String read(@PathVariable("boardNo") int boardNo) {
        log.info("boardNo: " + boardNo);

        // 뷰 이름 지정
        return "board/read";
    }


    @GetMapping("/formHome")
    public String forHome() {

        return "board/formHome";
    }

    @GetMapping("/register2")
    public String register2() {

        return "board/formHome";
    }
    /*
     * Params 매핑 -요청 파라미터를 매핑 조건으로 지정하는 경우 Params 속성을 사용함 -버튼이나 링크에 따라 호풀할 메서드를 바꿔야
     * 할 때 사용
     */

    // register
    @GetMapping(value = "/post", params = "register")
    public String getRegister() {
        log.info("register");
        return "board/formHome";
    }

    // modify
    @GetMapping(value = "post", params = "modify")
    public String getModify() {
        log.info("modify");
        return "board/formHome";
    }

    /*
    Header 매핑
    요청 헤더를 매칭 조건으로 지정하는 경우에는 headers 속성을 사용함
     */
    @RequestMapping("/ajaxHome")
    public String ajaxHome() {
        log.info("ajax에서 왔다.");
        return "board/ajaxHome";
    }

    //success: function(result){}
    //요청 uri : http://localhost/board/7
    //경로변수 : 7 (동적변수)

    //보통 헤더에서 보낼려고 하면 @responseBody를 사용해서 json데이터로 리턴
    @ResponseBody
    @RequestMapping(value = "/{boardNo}", method = RequestMethod.PUT, headers = "Method-Override=PUT")
    public ResponseEntity<String> ajaxModify(@PathVariable("boardNo") int boardNo) {
        log.info("ajaxModify에 왔다.");
        log.info("boardNo : " + boardNo);
        // success: function(result) {
        // 헤더에 String 데이터를 넣음
        ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

        return entity;
    }
    //요청 => 달러.get("board/"+boardNo,function(data){
    //요청 uri : http://localhost/board/7

    @RequestMapping(value = "/{boardNo}", method = RequestMethod.GET)
    public ResponseEntity<BookVO> getRead(@PathVariable("boardNo") int boardNo) {
        log.info("boardNO" + boardNo);

        bookVO.setBookId(7);
        bookVO.setTitle("천원짜리 변호사");
        bookVO.setCategory("드라마");
        bookVO.setPrice(10000);
        bookVO.setInsertDate(new Date("2022-10-25"));

        //vo를 응답데이터에 포함해보자
        ResponseEntity<BookVO> entity = new ResponseEntity<BookVO>(bookVO, HttpStatus.OK);
        return entity;
    }

    /*@responsebody를 붙이면
     * ResponseEntity<BookVO> entity = new ResponseEntity<BookVO>(bookVO, HttpStatus.OK);
     */


    //요청 uri : /board/getBook
    //data : {"boardId":7}
    //contentType : application/json;charset:utf-8
    //da(응)ta(답) type : json

    @ResponseBody
    @PostMapping("/getBook")
    public BookVO getBook(@RequestBody String boardNo) { //boardNo : data(boardId)
        log.info("boardNo: :" + boardNo);

        List<BookVO> bookVOList = new ArrayList<>();
        BookVO bookVO1 = new BookVO();
        bookVO1.setBookId(7);
        bookVO1.setTitle("천원짜리 변호사");
        bookVO1.setCategory("드라마");
        bookVO1.setPrice(10000);
        bookVO1.setInsertDate(new Date()
        );

        bookVOList.add(bookVO1);

        bookVO.setBookId(8);
        bookVO.setTitle("천원짜리 변호사");
        bookVO.setCategory("드라마");
        bookVO.setPrice(10000);
        bookVO.setInsertDate(new Date());

        return bookVO1;
    }
}
