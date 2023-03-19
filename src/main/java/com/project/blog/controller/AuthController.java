package com.project.blog.controller;

import com.project.blog.config.AppConfig;
import com.project.blog.dto.request.Login;
import com.project.blog.dto.request.SignUp;
import com.project.blog.dto.response.SessionResponse;
import com.project.blog.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Key;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppConfig appConfig;


    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody @Valid Login login) {
        Long userId = authService.signIn(login);
        Key accessToken = Keys.secretKeyFor(SignatureAlgorithm.HS256);//키 값을 고정하고 사용해야된다.

        String jws = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(Keys.hmacShaKeyFor(appConfig.getJwtKey()))
                .setIssuedAt(new Date())
                .compact();

        return new SessionResponse(jws);
    }

    @PostMapping("/auth/signup")
    public void signUp(@RequestBody SignUp signUp){
        authService.signUp(signUp);
    }

}
