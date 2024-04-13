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

    /**
     * 상품을 생성을 한다.
     * 전략에 따라서 쿠폰의 결제 로직이 변경이 된다.
     */
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

    /**
     * 상품의 가격을 변경을 합니다.
     * PriceHistory에 기존의 가격과 변경 시간이 저장
     * 가격이 변경이 되면서 개별 주문 단가, 총합이 변경
     */
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

    /**
     * 삭제
     */
    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    /**
     * @Modifying을 통하여 Bulk Update를 하였을 때 N+1 문제 해결
     * 쿠폰을 사용을 하였을 때 상품의 개수 감소
     */
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

    /**
     * 특정 시간을 조회 만약에 LocalDateTime이 처음에 없으면 Now로 주입
     */
    @Override
    @Transactional(readOnly = true)
    public ProductTimeResponse getProductInfoWhenTargetDate(Long productId, LocalDateTime targetDateTime) {
        Product product = productRepository.findProductWithRecentPriceHistory(productId, targetDateTime)
                .orElseThrow(() -> new NotFoundProductId(productId));

        PriceHistory latestPriceHistory = product.getPriceHistories().get(0);

        return ProductTimeResponse.of(product.getName(), latestPriceHistory.getProductPrice(), latestPriceHistory.getTargetTime());
    }
}
