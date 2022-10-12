package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RptpController {

    @GetMapping("/register/add")
    public String  ReisterController() {
        return "registerForm";
    }

    @GetMapping("/test/add")
    public String testController(){
        return "test";
    }
}