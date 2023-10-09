package com.example.jwtsecurity.config;


import com.example.jwtsecurity.jwt.filter.JwtAuthenticationFilter;
import com.example.jwtsecurity.jwt.provider.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfig extends AbstractHttpConfigurer<AuthenticationManagerConfig, HttpSecurity> {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

        builder.addFilterBefore(
                new JwtAuthenticationFilter(authenticationManager),
                UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(jwtAuthenticationProvider);
    }
    //manager를 만들고 jwtAuthenticationFilter이전에 UsernamePasswordAuthenticationFilter앞에 JwtAuthenticationFilter를 사용하는데 이 필터는 매니저를 가지고 있고
    //filter가 manager를 사용하고 manager가 provider를 사용하게 해준다.
}
