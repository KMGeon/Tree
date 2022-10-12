package com.example.spring.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URLEncoder;

@Controller
public class RegisterController {
    //@RequestMapping(value = "/register/add" , method ={RequestMethod.GET, RequestMethod.POST}) request method 겟 포스트 둘다 허용
    //@RequestMapping("/register/add") //신규회원 가입 화명
//    @GetMapping("/register/add")
//    public String register(){
//        return "registerForm";
//    }
    // @RequestMapping(value = "/register/save" ,method = RequstMethod.POST) 포스트만 하용 그래서 쿼리 스트링의 비번을 보호
    @PostMapping("/register/save")
    public String save(User user, Model m) throws Exception {
        //1.유효성 검사
        if (!isValid(user)) {
            String msg = URLEncoder.encode("잘못입력", "utf-8");
            m.addAttribute("msg", msg);
            return "redirect:/register/add";
//              return "redirect:/register/add?msg"+msg; //url 재작성
        }
        return "registerInfo";
    }

    private boolean isValid(User user) {
        return true;
    }
}
