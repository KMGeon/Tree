package com.example.mybatis.mapper;

import com.example.mybatis.domain.Board;
import com.example.mybatis.dto.BoardResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {

    List<BoardResponseDto>getList();
}
