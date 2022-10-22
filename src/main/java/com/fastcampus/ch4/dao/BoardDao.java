package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;

public interface BoardDao {
    BoardDto select(int bno) throws Exception;
}
