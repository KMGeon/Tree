package com.example.test.controller;

import com.example.test.dto.TestDTO;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestService testService;


    @GetMapping("/test")
    public  String welcomePage(Model model){

        List<TestDTO>list =this.testService.getList();
        model.addAttribute("list",list);
        return  "index";
    }

    @GetMapping("/boardForm.do")
    public String InsertForm(){
        int result = this.testService.InsertBook(dto);
        String url = "";
        if(result>1){
            url="redirect:/test";
        }else{
            url = "test";
        }
        return url;
    }

}
