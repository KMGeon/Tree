package dev.test.aswemake.domain.service.product;

import dev.test.aswemake.config.ApplicationTestBase;
import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import dev.test.aswemake.domain.entity.product.PriceHistory;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.global.exception.product.NotFoundProductId;
import dev.test.aswemake.global.exception.product.NotFoundProductName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
class ProductServiceImplTest extends ApplicationTestBase {

    private static final String DEFAULT_NAME = "기본이름";
    private static final Integer DEFAULT_PRICE = 1000;
    private static final Integer DEFAULT_QUANTITY = 10;
    private static final ProductStrategy DEFAULT_PRODUCTSTRATEGY = ProductStrategy.TOTAL;

    @Test
    public void 쿠폰_생성하기() throws Exception {
        //given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name(DEFAULT_NAME)
                .price(DEFAULT_PRICE)
                .productQuantity(DEFAULT_QUANTITY)
                .productStrategy(DEFAULT_PRODUCTSTRATEGY)
                .build();
        //when
        productService.createProduct(productCreateRequest);

        //Then
        Product product = productRepository.findByName(DEFAULT_NAME)
                .orElseThrow(() -> new NotFoundProductName(DEFAULT_NAME));


        assertThat(productRepository.count()).isNotNull();
        assertThat(product.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(product.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(product.getProductStrategy()).isEqualTo(DEFAULT_PRODUCTSTRATEGY);
        assertThat(product.getProductQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }


    @Test
    public void 상품_업데이트_성공() throws Exception {
        //given
        ProductUpdateRequest productUpdateRequest = ProductUpdateRequest.builder()
                .name(DEFAULT_NAME)
                .price(DEFAULT_PRICE)
                .build();

        Long productId = 1L;

        LocalDateTime target = LocalDateTime.of(2023, 12, 12, 12, 12, 11);
        //when
        productService.updateProduct(productUpdateRequest, productId, target);
        //Then

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundProductId(productId));

        List<PriceHistory> history = priceHistoryRepository.findLatestPriceHistory(product, target);

        assertThat(product.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(product.getPrice()).isEqualTo(DEFAULT_PRICE);

        for (PriceHistory priceHistory : history) {
            assertAll(
                    () -> assertThat(priceHistory.getProductPrice()).isIn(1000, 3333, 1111, 2222),
                    () -> assertThat(priceHistory.getId()).isPositive(),
                    () -> assertThat(priceHistory.getTargetTime()).isNotNull()
            );
        }
    }

    @Test
    public void 상품_업데이트_실패() {
        //given
        ProductUpdateRequest productUpdateRequest = ProductUpdateRequest.builder()
                .name(DEFAULT_NAME)
                .price(DEFAULT_PRICE)
                .build();

        Long productId = 99L;

        LocalDateTime target = LocalDateTime.of(2023, 12, 12, 12, 12, 11);
        //when
        //Then
        assertThatThrownBy(() -> productService.updateProduct(productUpdateRequest, productId, target))
                .isInstanceOf(NotFoundProductId.class)
                .hasMessage("상품의 ID를 찾을 수 없습니다. : 99");
    }

    @Test
    public void 상품_삭제하기() throws Exception {
        //given
        Long productId = 3L;
        //when
        productService.deleteProduct(productId);
        //Then
        assertThat(productRepository.count()).isNotNull();
    }

    /**
     * 1	1111	2023-09-04 10:11:11.000000	1
     * 2	2222	2023-09-05 05:30:00.000000	1
     * 3	3333	2023-08-05 07:24:11.000000	1
     * @throws Exception
     */
    @Test
    public void 특정시점의_상품의_가격과_시간을_조회_현재시간을_기준으로_조회() throws Exception {
        //given
        Long productId = 1L;
        LocalDateTime now = LocalDateTime.now();
        //when
        ProductTimeResponse response = productService.getProductInfoWhenTargetDate(productId, now);

        //Then
        LocalDateTime exceptTime = LocalDateTime.of(2023, 9, 5, 5, 30, 0);
        assertThat(response.getProductName()).isEqualTo("사과");
        assertThat(response.getProductPrice()).isEqualTo(3333);
        assertThat(response.getTargetDateTime()).isEqualTo(exceptTime);
    }


    @Test
    public void 특정시점의_상품의_가격과_시간을_조회_특정시간2_조회() throws Exception {
        //given
        Long productId = 1L;
        LocalDateTime time = LocalDateTime.of(2023, 8, 5, 7, 24, 11);

        //when
        ProductTimeResponse response = productService.getProductInfoWhenTargetDate(productId, time);

        //Then
        assertThat(response.getProductName()).isEqualTo("사과");
        assertThat(response.getProductPrice()).isEqualTo(2222);
        assertThat(response.getTargetDateTime()).isEqualTo(time);
    }

    @Test
    public void 특정시점의_상품의_가격과_시간을_조회_특정시간1_조회() throws Exception {
        //given
        Long productId = 1L;
        LocalDateTime time = LocalDateTime.of(2023, 9, 4, 10, 11, 11);
        //when
        ProductTimeResponse response = productService.getProductInfoWhenTargetDate(productId, time);

        //Then
        assertThat(response.getProductName()).isEqualTo("사과");
        assertThat(response.getProductPrice()).isEqualTo(1111);
        assertThat(response.getTargetDateTime()).isEqualTo(time);
    }

    @Test
    public void 변동된_가격기록_저장하기() throws Exception{
        //given
        Product product = productRepository.findById(1L)
                .orElseThrow(() -> new NotFoundProductId(1L));
        //when
        priceHistoryService.savePriceHistoryForProduct(1000, product);
        //Then
        assertThat(priceHistoryRepository.count()).isNotNull();
    }
}