package com.example.test.service;

import com.example.test.dto.LoginMemberDto;
import com.example.test.dto.TestDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TestService {
    public List<TestDTO> getList();

    public  int registerCheck(String memId);
}
