package dev.test.aswemake.domain.entity.product;

import dev.test.aswemake.domain.entity.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int productPrice;

    private LocalDateTime targetTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public PriceHistory(Long id, int productPrice, LocalDateTime targetTime, Product product) {
        this.id = id;
        this.productPrice = productPrice;
        this.targetTime = targetTime;
        this.product = product;
    }
}
