package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.entity.product.PriceHistory;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.repository.PriceHistoryRepository;
import dev.test.aswemake.domain.service.PriceHistroyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceHistoryServiceImpl implements PriceHistroyService {

    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistoryServiceImpl(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    @Transactional
    public void savePriceHistoryForProduct(Product product) {
        priceHistoryRepository.save(PriceHistory.builder()
                .productPrice(product.getPrice())
                .product(product)
                .build());
    }
}
