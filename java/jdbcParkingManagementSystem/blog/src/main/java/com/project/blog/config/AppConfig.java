package com.project.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
@Data
@ConfigurationProperties(prefix = "mugeon")
public class AppConfig {

    private byte[] jwtKey;

    public void setJwtKey(String jwtKey) {
        Base64.getDecoder().decode(jwtKey);
    }

    public byte[] getJwtKey() {
        return jwtKey;
    }
}
