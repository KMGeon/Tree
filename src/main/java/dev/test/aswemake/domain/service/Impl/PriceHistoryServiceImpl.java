package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.entity.product.PriceHistory;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.repository.PriceHistoryRepository;
import dev.test.aswemake.domain.service.PriceHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistoryServiceImpl(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    /**
     * ProductServiceImpl - {updateProduct}
     * - 상품의 가격이 변경을 한다면 PriceHistory는 변경한다.
     */
    @Override
    @Transactional
    public void savePriceHistoryForProduct(int productPrice,Product product) {
        priceHistoryRepository.save(PriceHistory.builder()
                .productPrice(productPrice)
                .targetTime(product.getModifiedDate())
                .product(product)
                .build());
    }
}
