package com.example.test.dao;

import com.example.test.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
    public  int registerCheck(String email);
    public int register(MemberDto memberDto);
}
