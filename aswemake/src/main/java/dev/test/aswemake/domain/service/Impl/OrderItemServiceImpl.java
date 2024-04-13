package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.repository.OrderItemRepository;
import dev.test.aswemake.domain.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * orderServiceImpl에 createOrder에 사용한다.
     */
    @Override
    @Transactional
    public void orderItemsSave(List<OrderItem> orderItems) {
        Objects.requireNonNull(orderItems, "orderItemsSave에서 orderItems Null");
        orderItemRepository.saveAll(orderItems);
    }
}
