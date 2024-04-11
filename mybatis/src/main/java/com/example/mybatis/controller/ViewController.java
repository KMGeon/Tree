package com.example.mybatis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ViewController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String rootView() {
        logger.info("====== /viewController [" + getClass().getSimpleName() + ".index()] start ======");
        logger.info("====== /viewController [" + getClass().getSimpleName() + ".index()] end ======");
        return "index";
    }

    @GetMapping("/insert")
    public String insertView() {
        logger.info("====== /viewController [" + getClass().getSimpleName() + "insert()] start ======");
        logger.info("====== /viewController [" + getClass().getSimpleName() + "insert()] end ======");
        return "insert";
    }

    @GetMapping("content/{id}")
    public String pathContent(@PathVariable("id") int id) {
        logger.info("====== /viewController [" + getClass().getSimpleName() + "content()] start ======");
        logger.info("====== /viewController [" + getClass().getSimpleName() + "content()] end ======");
        return "boardDetail";
    }

}
