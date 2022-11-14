package com.example.test.service.Impl;

import com.example.test.dao.TestMapper;
import com.example.test.dto.LoginMemberDto;
import com.example.test.dto.TestDTO;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public List<TestDTO> getList() {
        return this.testMapper.getList();
    }



    @Override
    public  int registerCheck(String memId){
        return this.testMapper.registerCheck(memId);
    }
}
