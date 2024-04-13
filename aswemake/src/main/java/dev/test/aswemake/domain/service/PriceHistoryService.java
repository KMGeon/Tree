package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.entity.product.Product;


public interface PriceHistoryService {
    void savePriceHistoryForProduct(int productPrice, Product product);
}
