package com.example.test.service.Impl;

import com.example.test.dao.MemberMapper;
import com.example.test.dto.MemberDto;
import com.example.test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;
    public int registerCheck(String email) {
        return this.memberMapper.registerCheck(email);
    }

    @Override
    public int register(MemberDto memberDto) {
        return this.memberMapper.register(memberDto);
    }
}
