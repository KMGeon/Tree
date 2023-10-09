package com.jpabook.jpashop;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Delivery;
import com.jpabook.jpashop.domain.OrderStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaProjectApplication.class, args);
    }

}
