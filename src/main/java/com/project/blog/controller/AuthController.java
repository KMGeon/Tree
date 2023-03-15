package com.project.blog.controller;

import com.project.blog.dto.request.Login;
import com.project.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/auth/login")
    public void login(@RequestBody @Valid Login login){
        log.info(">>login={}",login);
        //json 아이디/비밀번호
        authService.signIn(login);
        //db에서 조회하여

        //토큰을 응답하는 형태
    }
}
