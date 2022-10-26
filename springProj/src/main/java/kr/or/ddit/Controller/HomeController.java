package kr.or.ddit.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class HomeController {
    /*
        응답
        1. void 타입 : 호출하는 url과 통일한 뷰 이름을 나타냄
     */
    @GetMapping("/goHome0101")
    public void home0101() {
        log.info("home에 왔다.");
        //return "goHome0101"
    }

    @GetMapping("/goHome0102")
    public void home0102() {
        log.info("home에 왔다.");
        //return "goHome0101"
    }

    @GetMapping("/goHome0201")
    public String home0210() {
        log.info("home0201에 왔다.");
        return "goHome0101";
    }
}
