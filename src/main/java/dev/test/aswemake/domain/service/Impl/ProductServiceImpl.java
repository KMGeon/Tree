package dev.test.aswemake.domain.service.Impl;

import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.order.OrderItem;
import dev.test.aswemake.domain.entity.product.PriceHistory;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.repository.PriceHistoryRepository;
import dev.test.aswemake.domain.repository.ProductRepository;
import dev.test.aswemake.domain.service.ProductService;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.product.NotFoundProductId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, PriceHistoryRepository priceHistoryRepository) {
        this.productRepository = productRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    @Transactional
    public void createProduct(ProductCreateRequest productCreateRequest, LoginUserDto loginUserDto) {

        Product product = Product.builder()
                .name(productCreateRequest.getName())
                .price(productCreateRequest.getPrice())
                .productQuantity(productCreateRequest.getProductQuantity())
                .build();

        Optional.ofNullable(productCreateRequest.getCouponUseStatus())
                .ifPresent(couponUseStatus -> product.isCouponApplicableToProduct());

        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(ProductUpdateRequest productUpdateRequest, Long productId, LocalDateTime now) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundProductId(productId));

        PriceHistory priceHistory = PriceHistory.builder()
                .productPrice(productUpdateRequest.getPrice())
                .targetTime(product.getModifiedDate())
                .product(product)
                .build();

        priceHistoryRepository.save(priceHistory);

        product.updateProduct(productUpdateRequest);


        List<OrderItem> orderItems = product.getOrderItems();

        for (OrderItem orderItem : orderItems) {
            int newOrderPrice = product.getPrice() * orderItem.getProductCount();
            orderItem.setOrderPrice(newOrderPrice);
        }

        for (OrderItem orderItem : orderItems) {
            Order order = orderItem.getOrder();
            List<OrderItem> items = order.getOrderItems();
            int totalCost = items.stream()
                    .mapToInt(item -> item.getOrderPrice() * item.getProductCount()).sum();
            order.setTotalCost(totalCost);
        }

    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductTimeResponse getProductInfoWhenTargetDate(Long productId, LocalDateTime targetDateTime) {
        Product product = productRepository.findById(productId)
                .orElseThrow();
        List<PriceHistory> priceHistoryList = priceHistoryRepository.findLatestPriceHistory(product, targetDateTime);
        return ProductTimeResponse.of(product.getName(), priceHistoryList.get(0).getProductPrice(), priceHistoryList.get(0).getTargetTime());
    }
}
