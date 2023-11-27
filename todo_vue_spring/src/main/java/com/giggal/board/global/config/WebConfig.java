package com.giggal.board.global.config;


import com.giggal.board.domain.admin.mapper.AdminMapper;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.global.interceptor.AdminCheckInterceptor;
import com.giggal.board.global.jwt.util.JwtTokenizer;
import com.giggal.board.global.util.IfLoginArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//Cors 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AdminMapper adminMapper;
    private final JwtTokenizer jwtTokenizer;
    private final MemberRepository memberRepository;

    public WebConfig(AdminMapper adminMapper, JwtTokenizer jwtTokenizer, MemberRepository memberRepository) {
        this.adminMapper = adminMapper;
        this.jwtTokenizer = jwtTokenizer;
        this.memberRepository = memberRepository;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS", "DELETE");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new IfLoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminCheckInterceptor(adminMapper, jwtTokenizer, memberRepository))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/signup","/api/login","/");
    }
}
