package com.example.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MemberDto {
    private  String email;
    private  String password;
    private  String Phone;
    private  String birth;
}
