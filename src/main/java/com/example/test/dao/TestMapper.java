package com.example.test.dao;

import com.example.test.dto.TestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<TestDTO> getList();

    public int InsertBook();
}
