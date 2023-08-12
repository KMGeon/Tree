package kr.or.ddit.Controller;


import kr.or.ddit.vo.BookVO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//프링아 자바빈으로 관리해줘
@Slf4j
@Controller
public class HomeController {

    @Autowired
    BookVO bookV1;

    //http://localhost/
    @RequestMapping("/")
    public String home() {
        return "index";
    }


    /* 1. void 타입
     호출하는 URL과 동일한 뷰 이름을 나타냄
     */
    @RequestMapping(value = "/goHome0101", method = RequestMethod.GET)
    public void home0101() {
        log.info("home0101에 왔다");
        //return "goHome0101";
    }

    @RequestMapping(value = "/goHome0102", method = RequestMethod.GET)
    public void home0102() {
        log.info("home0102에 왔다");
        //return "goHome0102";
    }

    /* 2. String 타입
         뷰 파일의 경로와 파일 이름을 나타내기 위해 사용
     */
    @RequestMapping(value = "/goHome0201", method = RequestMethod.GET)
    public String home0201() {
        log.info("home0201에 왔다");
        //forwarding
        return "goHome0201";
    }

    @RequestMapping(value = "/sub/goHome0202", method = RequestMethod.GET)
    public String home0202() {
        log.info("home0202에 왔다");
        //forwarding
        return "goHome0202";
    }

    /*
    자바빈즈 클래스 타입
    json 타입의 데이터를 만들어서 반환하는 용도로 사용
    json객체 타입의 데이터를 만들어서 반환하는 용도
    pom.xml에 jackson-databind 의존 라이브러리가 추가되어 있어야함
     */
    @ResponseBody
    @RequestMapping(value = "/goHome0301", method = RequestMethod.GET)
    public BookVO home0301() {
        BookVO bookVO = new BookVO();
        bookVO.setBookId(7);
        bookVO.setTitle("천원짜리 변호사");
        bookVO.setCategory("드라마");
        bookVO.setPrice(10000);
        bookVO.setInsertDate(new Date());
        return bookVO;
    }

    /* 4. 컬렉션 List 타입
        JSON 객체 배열 타입의 데이터를 만들어서 반환하는 용도로 사용
     */
    @ResponseBody
    @RequestMapping(value = "goHome0401", method = RequestMethod.GET)
    public List<BookVO> home0401() {
        log.info("home0401");

        List<BookVO> list = new ArrayList<>();

        BookVO bookVO = new BookVO();
        bookVO.setBookId(7);
        bookVO.setTitle("천원짜리 변호사");
        bookVO.setCategory("드라마");
        bookVO.setPrice(10000);
        bookVO.setInsertDate(new Date());

        list.add(bookVO); // 0

        bookVO = new BookVO();
        bookVO.setBookId(8);
        bookVO.setTitle("나의 해방일지");
        bookVO.setCategory("드라마");
        bookVO.setPrice(12000);
        bookVO.setInsertDate(new Date());

        list.add(bookVO); // 1

        return list;
    }

    /* 5. Map 타입
     Map 형태의 자료를 JSON 객체 타입의 데이터로 만들어서 반환
     responseBody : json객체타입으로 바뀐다.
     */
    @ResponseBody
    @RequestMapping(value = "/goHome0501", method = RequestMethod.GET)
    public Map<String, BookVO> home0501() {
        log.info("home0501에 왔다");

        Map<String, BookVO> map = new HashMap<String, BookVO>();

        BookVO bookVO = new BookVO();
        bookVO.setBookId(7);
        bookVO.setTitle("천원짜리 변호사");
        bookVO.setCategory("드라마");
        bookVO.setPrice(10000);
        bookVO.setInsertDate(new Date());

        map.put("key1", bookVO);

        bookVO = new BookVO();
        bookVO.setBookId(8);
        bookVO.setTitle("나의 해방일지");
        bookVO.setCategory("드라마");
        bookVO.setPrice(12000);
        bookVO.setInsertDate(new Date());

        map.put("key2", bookVO);

        return map;
    }

    /*
    6.ResponseEntity<Void>타입
        response 할 때 http헤더 정보와 내용을 가공 시 사용
     */
    @ResponseBody
    //@RequestMapping(value = "goHome0601" , method = RequestMethod.GET)
    @GetMapping("goHome0601")
    public ResponseEntity<Void> home0601() {
        log.info("home0601입니다.");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    /*
    6.ResponseEntity<Void>타입
        response 할 때 http헤더 정보와 내용을 가공 시 사용
     */
    @ResponseBody
    @RequestMapping(value = "goHome0701", method = RequestMethod.GET)
    public ResponseEntity<String> home0701() {
        log.info("home0601입니다.");
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }


    //객체의 json타입의 데이터
    @ResponseBody
    @GetMapping("goHome0801")
    public ResponseEntity<BookVO> home0801() {

        BookVO bookV1 = new BookVO();
        bookV1.setBookId(8);
        bookV1.setTitle("나의 해방일지");
        bookV1.setCategory("드라마");
        bookV1.setPrice(12000);
        bookV1.setInsertDate(new Date());
        return new ResponseEntity<BookVO>(bookV1, HttpStatus.OK);
    }


    /*
    List와 Map을 응용하여 json타입의 데이터로 응답할 수 있음
    11.responseEntity<byte>[]타입
    response할 때 http헤더 정보와 바이너리 파일 데이터를 전달할 수 있음
     */
    @GetMapping("goHome11011")
    public ResponseEntity<byte[]> home1101() throws IOException {
        log.info("1101에서 왔다");
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        HttpHeaders headers = new HttpHeaders();

        try {
            in = new FileInputStream("C:\\eclipse-jee-2020-06-R-win32-x86_64\\workspace\\springProj\\src\\main\\webapp\\resources\\upload\\haha.png");
            headers.setContentType(MediaType.IMAGE_PNG);
            //common.io
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {

            in.close();
        }
        return entity;
    }

    //파일의 데이터를 웹 브라우저가 다운로드 받도록 해보자
    @ResponseBody
    @GetMapping("goHome1102")
    public ResponseEntity<byte[]> home1102() throws IOException {
        log.info("home1102에 왔다.");
        String fileName = "이집트.7z";
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            in = new FileInputStream("C:\\eclipse-jee-2020-06-R-win32-x86_64\\workspace\\springProj\\src\\main\\webapp\\resources\\upload\\이집트.7z");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Dispostion", "attachment;filename=\"" +
                    new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }
        return entity;
    }
}






