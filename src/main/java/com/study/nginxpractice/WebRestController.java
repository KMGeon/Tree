package com.study.nginxpractice;


import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class WebRestController {

    private final Environment env;

    public WebRestController(Environment env) {
        this.env = env;
    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}