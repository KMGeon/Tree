package dev.test.aswemake.domain.repository;

import dev.test.aswemake.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN FETCH p.priceHistories ph " +
            "WHERE p.id = :productId " +
            "AND ph.targetTime <= :timestamp " +
            "ORDER BY ph.targetTime DESC")
    Optional<Product> findProductWithRecentPriceHistory(@Param("productId") Long productId,
                                                        @Param("timestamp") LocalDateTime timestamp);

    @Modifying
    @Query("UPDATE Product p " +
            " SET p.productQuantity " +
            " = p.productQuantity - :quantity " +
            " WHERE p.id = :productId ")
    void declineProductQuantity(@Param("productId") Long productId, @Param("quantity") int quantity);
}
