package com.study.nginxpractice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class WebRestController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

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


    @GetMapping("/test2")
    public String test2() throws InterruptedException {
        logger.info("=================start==================");
        for (int i=0; i< 30; i++){
            logger.info("test2 : {}" , i);
            Thread.sleep(1000);
        }
        logger.info("=================end==================");
        return "success";
    }

    @GetMapping("/profile2")
    public String profile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles());

        for (String profile : profiles) {
            System.out.println("profile = " + profile);
        }

        List<String> realProfiles = Arrays.asList("real1", "real2");
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }

}