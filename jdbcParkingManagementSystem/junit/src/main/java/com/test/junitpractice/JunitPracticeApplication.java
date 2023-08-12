package com.test.junitpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JunitPracticeApplication {

    void sound() {
        System.out.println("하하");
    }

    public static void main(String[] args) {
        new JunitPracticeApplication().sound();
        SpringApplication.run(JunitPracticeApplication.class, args);
    }

}
