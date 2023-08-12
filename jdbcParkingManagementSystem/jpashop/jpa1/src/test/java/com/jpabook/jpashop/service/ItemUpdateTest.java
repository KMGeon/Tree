package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@Transactional(readOnly = true)
@SpringBootTest
public class ItemUpdateTest {
    @Autowired
    EntityManager entityManager;
    @Test
    public void updateTest () throws Exception{
        //given
        Book book = entityManager.find(Book.class, 1L);
        //when
        book.setName("asdf");
        //Then
    }
}
