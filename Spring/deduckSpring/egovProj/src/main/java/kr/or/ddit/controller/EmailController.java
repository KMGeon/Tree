package kr.or.ddit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.util.EmailUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmailController {

    // @RequiredArgsConstructor 를 사용해서 생성자를 통한 DI
    private final EmailUtil emailUtil;

    // @RequestMapping(value = "/send/eMail", method = RequestMethod.GET)
    @GetMapping("/sendmail")
    public String sendEmail() {
        // 메일 주소(받는 주소), 제목, 내용
        emailUtil.sendEmail("메일 받는 주소@naver.com", "E-Mail Test", "나는 메일 내용이다!!!");
        return "Success Send E-Mail";
    }
}
