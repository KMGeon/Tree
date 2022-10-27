package com.example.spring.ch2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class User {
    private String id;
    private String pwd;
    private  String name;
    private String email;
    private String birth;
    private String sns;

    public User(String id, String pwd, String name, String email, String birth, String sns) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.sns = sns;
    }
}
