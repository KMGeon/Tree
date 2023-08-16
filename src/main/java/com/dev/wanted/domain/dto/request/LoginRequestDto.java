package com.dev.wanted.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotEmpty(message = "아이디를 입력하세요")
    @Email(message = "유효한 이메일 주소를 입력하세요")
    private String account;

    @NotEmpty(message = "비밀번호를 입력하세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력하세요")
    private String password;
}
