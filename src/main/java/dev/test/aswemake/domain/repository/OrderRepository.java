package dev.test.aswemake.domain.repository;

import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query("UPDATE Order o SET o.totalCost = :totalCost WHERE o.id IN (SELECT oi.order.id FROM OrderItem oi WHERE oi.product = :product)")
    void updateOrdersByProduct(@Param("totalCost") int totalCost, @Param("product") Product product);
}
