package dev.test.aswemake.domain.repository;

import dev.test.aswemake.domain.entity.product.PriceHistory;
import dev.test.aswemake.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    @Query("SELECT ph FROM PriceHistory ph " +
            "WHERE ph.product = :product " +
            "AND ph.targetTime < :timestamp " +
            "ORDER BY ph.targetTime DESC")
    List<PriceHistory> findLatestPriceHistory(@Param("product") Product product, @Param("timestamp") LocalDateTime timestamp);
}
