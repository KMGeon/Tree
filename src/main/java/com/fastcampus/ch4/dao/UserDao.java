package com.fastcampus.ch4.dao;



public interface UserDao {
    com.fastcampus.ch4.domain.User selectUser(String id) throws Exception;
    int deleteUser(String id) throws Exception;
    int insertUser(com.fastcampus.ch4.domain.User user) throws Exception;
    int updateUser(com.fastcampus.ch4.domain.User user) throws Exception;
    int count() throws Exception;
    void deleteAll() throws Exception;
}