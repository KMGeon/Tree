package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.entity.product.Product;


public interface PriceHistroyService {
    void savePriceHistoryForProduct(Product product);
}
