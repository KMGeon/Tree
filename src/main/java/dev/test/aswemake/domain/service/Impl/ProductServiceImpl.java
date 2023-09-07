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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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

        Product product = Product.builder()
                .name(productCreateRequest.getName())
                .price(productCreateRequest.getPrice())
                .productQuantity(productCreateRequest.getProductQuantity())
                .build();

        //couponUseStatus는 NULLABLE 하게 VALID를 지정을 했다. 왜냐하면 DEFAULT로 FALSE로 지정을 하였다.
        //만약에 couponUseStatus에 데이터가 들어가게 된다면 TRUE로 설정이 된다.
        Optional.ofNullable(productCreateRequest.getCouponUseStatus())
                .ifPresent(couponUseStatus -> product.isCouponApplicableToProduct());

        productRepository.save(product);
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

    /**
     * 쿠폰을 사용하여 결제를 하였을 때
     *
     * @param orderItems
     */
    @Override
    @Transactional
    public void declineProductQuantity(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            int quantity = orderItem.getProductCount();
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
