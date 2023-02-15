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
        Member member = createMember();

        Book book = createBook("책이름", 10000, 10);
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
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("책이름", 10000, 10);

        int orderCount = 10;
        //when
        orderService.order(member.getId(),item.getId(),orderCount);

        //Then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA" , 10000,10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //Then
        Order getOrder = orderRepository.findOnd(orderId);
        assertEquals("주문 취소시 상태는 Cancel이다." , OrderStatus.CANCEL , getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10 , item.getStockQuantity());
    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        Item item = new Book();
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}