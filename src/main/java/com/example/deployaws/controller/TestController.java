package com.example.deployaws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        log.info("=========================");
        log.info(">>>>>테스트<<<<<<<<<<");
        log.info("=========================");
        return "<h1>테스트</h1>";
    }
}
