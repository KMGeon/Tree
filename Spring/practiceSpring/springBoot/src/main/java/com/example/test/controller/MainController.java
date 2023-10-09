package com.example.test.controller;

import com.example.test.dto.MemberDto;
import com.example.test.service.MemberService;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    MemberService memberService;


    @RequestMapping(value = "/memJoin" , method = RequestMethod.GET)
    public String memJoin(){
        return "member/join";
    }

        @GetMapping("/memRegisterCheck")
    public @ResponseBody int registerCheck(@RequestParam("email") String email){
       int result = this.memberService.registerCheck(email);//아이디가 사용 가능하면 0 불가능하면 1
       return result;
    }
    @GetMapping("/login")
    public String join(MemberDto memberDto, RedirectAttributes attr,@RequestParam("password")String password,@RequestParam("password2")String password2){
        int result = this.memberService.register(memberDto);
        if(!password.equals(password2)){
            return "redirect:/";
        }
        if(result==1&&password.equals(password2)){
            return "redirect:/memJoin";
        }else{
            return "redirect:/";
        }

    }

}
