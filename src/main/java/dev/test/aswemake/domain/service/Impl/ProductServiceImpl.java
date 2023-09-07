package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.PriceHistory;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.repository.ProductRepository;
import dev.test.aswemake.domain.service.PriceHistoryService;
import dev.test.aswemake.domain.service.ProductService;
import dev.test.aswemake.global.exception.product.NotFoundProductId;
import dev.test.aswemake.global.exception.product.NotFullYetAboutQuantity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PriceHistoryService priceHistoryService;

    public ProductServiceImpl(
            ProductRepository productRepository,
            PriceHistoryService priceHistoryService
    ) {
        this.productRepository = productRepository;
        this.priceHistoryService = priceHistoryService;
    }

    @Override
    @Transactional
    public void createProduct(ProductCreateRequest productCreateRequest) {
        productRepository.save(Product.builder()
                .name(productCreateRequest.getName())
                .price(productCreateRequest.getPrice())
                .productQuantity(productCreateRequest.getProductQuantity())
                .productStrategy(productCreateRequest.getProductStrategy())
                .build());
    }

    @Override
    @Transactional
    public void updateProduct(ProductUpdateRequest productUpdateRequest, Long productId, LocalDateTime now) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundProductId(productId));

        priceHistoryService.savePriceHistoryForProduct(productUpdateRequest.getPrice(), product);

        product.updateProduct(productUpdateRequest);

        List<OrderItem> orderItems = product.getOrderItems();

        for (OrderItem orderItem : orderItems) {
            int newOrderPrice = product.getPrice() * orderItem.getProductCount();
            orderItem.setOrderPrice(newOrderPrice);
        }

        for (OrderItem orderItem : orderItems) {
            Order order = orderItem.getOrder();
            order.setTotalCost(order.getOrderItems().stream()
                    .mapToInt(item -> item.getOrderPrice() * item.getProductCount())
                    .sum());
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void declineProductQuantity(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            int quantity = orderItem.getProductCount();

            if (product.getProductQuantity() < quantity) {
                throw new NotFullYetAboutQuantity(quantity);
            }
            productRepository.declineProductQuantity(product.getId(), quantity);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ProductTimeResponse getProductInfoWhenTargetDate(Long productId, LocalDateTime targetDateTime) {
        Product product = productRepository.findProductWithRecentPriceHistory(productId, targetDateTime)
                .orElseThrow(() -> new NotFoundProductId(productId));

        PriceHistory latestPriceHistory = product.getPriceHistories().get(0);

        return ProductTimeResponse.of(product.getName(), latestPriceHistory.getProductPrice(), latestPriceHistory.getTargetTime());
    }
}
