package com.example.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class LoginMemberDto {
    private  String memId;
    private  String password;
    private  String address;
    private  String phone;
    private  int age;
}
