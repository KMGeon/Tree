package dev.test.aswemake.domain.controller;

import dev.test.aswemake.config.ControllerTest;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.domain.entity.enums.CouponStrategy;
import dev.test.aswemake.global.exception.ErrorResponse;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


class ProductControllerTest extends ControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        super.setup();
    }

    @AfterEach
    void cleanUp() {
        super.cleanup();
    }

    @Nested
    class getProductPriceAtTimestamp {

        @Test
        public void 변경된_상품_특정시간_조회하기() throws Exception {
            //given
            Long productId = 1L;
            LocalDateTime timestamp = LocalDateTime.of(2023, 9, 7, 15, 30, 0);

            ProductTimeResponse expectedResponse = ProductTimeResponse.of("귤", 1000, timestamp);


            //when
            given(productService.getProductInfoWhenTargetDate(anyLong(), eq(timestamp))).willReturn(expectedResponse);

            ApiResponse<ProductTimeResponse> response = productMockApiCaller.getProductPriceAtTimestamp();
            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(200),
                    () -> assertThat(response.getBody().getProductPrice()).isEqualTo(1000),
                    () -> assertThat(response.getBody().getProductName()).isEqualTo("귤"),
                    () -> assertThat(response.getBody().getTargetDateTime()).isEqualTo(timestamp)
            );
        }

    }


    @DisplayName("POST /api/product 상품 생성하기")
    @Nested
    class createProduct {

        @Test
        public void 상품_생성하기_성공() throws Exception {
            //given
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .name("귤")
                    .price(1000)
                    .productQuantity(20)
                    .couponStrategy(CouponStrategy.TOTAL)
                    .couponUseStatus("FIX")
                    .build();
            //when
            ApiResponse<Integer> response = productMockApiCaller.createProduct(request);

            //Then
            assertThat(response.getStatus()).isEqualTo(201);
            assertThat(productRepository.count()).isNotNull();
        }

        @Test
        public void 상품_생성하기_실패_유효성_이름() throws Exception {
            //given
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .price(1000)
                    .productQuantity(20)
                    .couponStrategy(CouponStrategy.TOTAL)
                    .couponUseStatus("FIX")
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.createProductArgumentNotValid(request);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("name")).isEqualTo("제품 제목을 입력하세요.")
            );
        }

        @Test
        public void 상품_생성하기_실패_유효성_가격() throws Exception {
            //given
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .name("귤")
                    .productQuantity(20)
                    .couponStrategy(CouponStrategy.TOTAL)
                    .couponUseStatus("FIX")
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.createProductArgumentNotValid(request);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("price")).isEqualTo("가격을 입력하세요.")
            );
        }

        @Test
        public void 상품_생성하기_실패_유효성_가격_음수() throws Exception {
            //given
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .name("귤")
                    .price(-1)
                    .productQuantity(20)
                    .couponStrategy(CouponStrategy.TOTAL)
                    .couponUseStatus("FIX")
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.createProductArgumentNotValid(request);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("price")).isEqualTo("가격은 양수여야 합니다.")
            );
        }


        @Test
        public void 상품_생성하기_실패_유효성_수량() throws Exception {
            //given
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .name("귤")
                    .price(1000)
                    .couponStrategy(CouponStrategy.TOTAL)
                    .couponUseStatus("FIX")
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.createProductArgumentNotValid(request);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("productQuantity")).isEqualTo("제품 수량을 입력하세요.")
            );
        }

        @Test
        public void 상품_생성하기_실패_유효성_수량_음수() throws Exception {
            //given
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .name("귤")
                    .price(1000)
                    .productQuantity(-20)
                    .couponStrategy(CouponStrategy.TOTAL)
                    .couponUseStatus("FIX")
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.createProductArgumentNotValid(request);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("productQuantity")).isEqualTo("제품 수량은 양수여야 합니다.")
            );
        }


        @Test
        public void 상품_생성하기_실패_유효성_쿠폰전략() throws Exception {
            //given
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .name("귤")
                    .price(1000)
                    .productQuantity(20)
                    .couponUseStatus("FIX")
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.createProductArgumentNotValid(request);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("couponStrategy")).isEqualTo("쿠폰 전략을 선택하세요.")
            );
        }
    }

    @DisplayName("PUT /api/product/{id}")
    @Nested
    class updateProduct {

        @Test
        public void 상품_업데이트_성공() throws Exception {
            //given
            ProductUpdateRequest request = ProductUpdateRequest.builder()
                    .name("변경 상품 이름")
                    .price(10000)
                    .build();

            Long productId = 1L;
            //when
            ApiResponse<Integer> response = productMockApiCaller.updateProduct(request, productId);
            //Then
            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(productRepository.count()).isNotNull();
        }

        @Test
        public void 상품_업데이트_실패_유효성_이름() throws Exception {
            //given
            ProductUpdateRequest request = ProductUpdateRequest.builder()
                    .price(10000)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.updateProductArgumentNotValid(request, 1L);
            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("name")).isEqualTo("변경 상품의 이름을 작성하세요.")
            );
        }

        @Test
        public void 상품_업데이트_실패_유효성_가격_음수() throws Exception {
            //given
            ProductUpdateRequest request = ProductUpdateRequest.builder()
                    .name("변경 상품 이름")
                    .price(-10000)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = productMockApiCaller.updateProductArgumentNotValid(request, 1L);
            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("price")).isEqualTo("변경 가격은 양수로 작성을 하세요.")
            );
        }

    }

    @DisplayName("DELETE /api/product/{id}")
    @Nested
    class deleteProduct {

        @Test
        public void 상품_삭제하게_성공() throws Exception {
            //when
            ApiResponse<Integer> response = productMockApiCaller.deleteProduct(1L);
            //Then
            assertThat(response.getStatus()).isEqualTo(204);
            assertThat(productRepository.count()).isNotNull();
        }
    }
}