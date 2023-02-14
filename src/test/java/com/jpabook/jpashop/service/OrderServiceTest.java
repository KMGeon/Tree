package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        Item item = new Book();
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        Book book = new Book();
        book.setName("책이름");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        //when
        int ordercount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), ordercount);
        //Then
        Order getOrder = orderRepository.findOnd(orderId);

        assertEquals("상품 주문시 상태는 order" , OrderStatus.ORDER , getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다." , 1 , getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다." , 10000*ordercount , getOrder.getTotalPrice());
        assertEquals("주문 수량 만큼 줄어야 한다.",8,book.getStockQuantity());
    }

    @Test
    public void 주문취소() throws Exception {
        //given

        //when

        //Then
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given

        //when

        //Then
    }

}