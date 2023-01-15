package com.example.test.service;

import com.example.test.dto.MemberDto;

public interface MemberService {
    public  int registerCheck(String email);
    public int register(MemberDto memberDto);
}
