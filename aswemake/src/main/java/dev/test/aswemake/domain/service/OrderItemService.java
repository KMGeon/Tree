package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.entity.order.OrderItem;

import java.util.List;

public interface OrderItemService {
    void orderItemsSave(List<OrderItem> orderItems);
}
