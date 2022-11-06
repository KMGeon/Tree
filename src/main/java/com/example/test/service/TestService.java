package com.example.test.service;

import com.example.test.dto.TestDTO;

import java.util.List;

public interface TestService {
    public List<TestDTO> getList();
    public int InsertBook(TestDTO dto);
}
