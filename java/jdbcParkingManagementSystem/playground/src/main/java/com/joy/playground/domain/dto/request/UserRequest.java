package com.joy.playground.domain.dto.request;

import com.joy.playground.domain.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "이메일 입력하세요")
    private String email;

    @NotBlank(message = "비밀번호 입력하세요")
    private String password;

    @NotBlank(message = "이름 입력하세요")
    private String name;

    public User from(){
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
