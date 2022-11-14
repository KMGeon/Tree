package com.example.test.controller;

import com.example.test.dto.LoginMemberDto;
import com.example.test.dto.TestDTO;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/12")
    public String welcome() {
        return "main";
    }
    @GetMapping("/memRegisterCheck")
    public @ResponseBody int registerCheck(@RequestParam("memId") String memId){
       int result = this.testService.registerCheck(memId);

       //아이디가 사용 가능하면 0 불가능하면 1
       return result;

    }


    @GetMapping("/test")
    public String welcomePage(Model model) {

        List<TestDTO> list = this.testService.getList();
        model.addAttribute("list", list);
        return "index";
    }



}
