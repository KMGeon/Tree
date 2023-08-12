package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest//springboot 컨테이너 안에서 사용
@Transactional
public class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 주문() throws Exception{
        //given

        //when

        //Then
    }

}