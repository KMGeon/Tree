package dev.test.aswemake.domain.repository;

import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Query("UPDATE OrderItem oi SET oi.orderPrice = :orderPrice WHERE oi.product = :product")
    void updateOrderItemsByProduct(@Param("orderPrice") int orderPrice, @Param("product") Product product);
}
