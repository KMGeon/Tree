package dev.test.aswemake.domain.controller;

import dev.test.aswemake.config.ControllerTest;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.PayProductRequest;
import dev.test.aswemake.domain.controller.dto.request.coupon.CouponCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.PayCouponInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.coupon.CouponOrderResponse;
import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


class CouponControllerTest extends ControllerTest {

    private static final String COUPON_NAME = "생일 쿠폰";
    private static final int SALE_PRICE = 1000;
    private static final CouponSaleStrategy COUPON_SALE_STRATEGY_FIX = CouponSaleStrategy.FIX;
    private static final CouponSaleStrategy COUPON_SALE_STRATEGY_RATE = CouponSaleStrategy.RATE;

    @BeforeEach
    void setUp() throws Exception {
        super.setup();
    }

    @Nested
    @DisplayName("POST /api/coupon")
    class createCoupon {
        @Test
        public void 쿠폰을_생성을_합니다() throws Exception {
            //given
            CouponCreateRequest couponCreateRequest = CouponCreateRequest.builder()
                    .couponName(COUPON_NAME)
                    .salePrice(SALE_PRICE)
                    .couponSaleStrategy(COUPON_SALE_STRATEGY_FIX)
                    .build();
            //when
            ApiResponse<Integer> coupon = couponMockApiCaller.createCoupon(couponCreateRequest);

            //Then
            assertThat(couponRepository.count()).isNotNull();
            assertThat(coupon.getStatus()).isEqualTo(201);
        }

        @Test
        public void 쿠폰을_생성에_실패_유효성_실패_쿠폰이름() throws Exception {
            CouponCreateRequest couponCreateRequest = CouponCreateRequest.builder()
                    .salePrice(SALE_PRICE)
                    .couponSaleStrategy(COUPON_SALE_STRATEGY_FIX)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.createCouponArgumentNotValid(couponCreateRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("couponName")).isEqualTo("쿠폰 이름을 입력하세요.")
            );
        }

        @Test
        public void 쿠폰을_생성에_실패_유효성_실패_쿠폰가격() throws Exception {
            CouponCreateRequest couponCreateRequest = CouponCreateRequest.builder()
                    .couponName(COUPON_NAME)
                    .couponSaleStrategy(COUPON_SALE_STRATEGY_FIX)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.createCouponArgumentNotValid(couponCreateRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("salePrice")).isEqualTo("쿠폰 할인가를 입력하세요.")
            );
        }

        @Test
        public void 쿠폰을_생성에_실패_유효성_실패_쿠폰전략() throws Exception {
            CouponCreateRequest couponCreateRequest = CouponCreateRequest.builder()
                    .couponName(COUPON_NAME)
                    .salePrice(SALE_PRICE)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.createCouponArgumentNotValid(couponCreateRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("couponSaleStrategy")).isEqualTo("RATE, FIX를 선택하세요.")
            );
        }

        @Test
        public void 쿠폰을_생성에_실패_쿠폰전략_비율_100을_넘으면_에러를_반환한다() throws Exception {
            CouponCreateRequest couponCreateRequest = CouponCreateRequest.builder()
                    .couponName(COUPON_NAME)
                    .salePrice(SALE_PRICE)
                    .couponSaleStrategy(COUPON_SALE_STRATEGY_RATE)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.createCouponArgumentNotValid(couponCreateRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("2001"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("쿠폰의 할인 비율이 100%가 넘는다. 1000"),
                    () -> assertThat(response.getBody().getDetailMessage().get("CouponAbstractException")).isEqualTo("쿠폰 관련 Exception")
            );
        }

        @Test
        public void 쿠폰을_생성에_실패_마켓권한_이외에_접근을_하였을때_오류가_반환된다() throws Exception {
            CouponCreateRequest couponCreateRequest = CouponCreateRequest.builder()
                    .couponName(COUPON_NAME)
                    .salePrice(SALE_PRICE)
                    .couponSaleStrategy(COUPON_SALE_STRATEGY_RATE)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.createCouponAccessDeniedException(couponCreateRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(403),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("403"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("Access Denied: Access is denied"),
                    () -> assertThat(response.getBody().getDetailMessage().get("Spring Security")).isEqualTo("권한이 일치하지 않습니다.")
            );
        }
    }

    @DisplayName("POST /api/coupon/pay")
    @Nested
    class payProductWithCoupon {

        private LoginUserDto loginUserDto;
        private PayProductRequest payProductRequest;
        private List<ProductOrderResponse> productOrderResponses;
        CouponOrderResponse couponOrderResponse;

        @BeforeEach
        void setUp() {
            loginUserDto = LoginUserDto.builder()
                    .memberId(1L)
                    .roles(List.of(RoleEnum.MARKET.getRoleName()))
                    .build();

            payProductRequest = PayProductRequest.builder()
                    .couponId(1L)
                    .orderId(1L)
                    .build();

            productOrderResponses = new ArrayList<>(Arrays.asList(
                    new ProductOrderResponse(1L, "사과", 1000, 10),
                    new ProductOrderResponse(2L, "배", 200, 7),
                    new ProductOrderResponse(3L, "귤", 500, 8),
                    new ProductOrderResponse(4L, "땅콩", 700, 9)
            ));

             couponOrderResponse = CouponOrderResponse.builder()
                    .couponId(1L)
                    .couponName("생일쿠폰")
                    .couponSalePrice(1000)
                    .build();
        }

        @Test
        public void 주문을_쿠폰을_이용하여_결제를_성공적으로_처리한다() throws Exception {
            //given
            given(couponService.processPaymentWithCoupon(eq(loginUserDto), eq(payProductRequest)))
                    .willReturn(PayCouponInfoResponse.builder()
                            .productOrderResponses(productOrderResponses)
                            .totalCost(10000)
                            .couponOrderResponse(couponOrderResponse)
                            .build());
            //when
            ApiResponse<PayCouponInfoResponse> response = couponMockApiCaller.payProductWithCoupon(payProductRequest);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(201),

                    () -> assertThat(response.getBody().getTotalCost()).isEqualTo(10000),

                    () -> {
                        CouponOrderResponse couponOrderResponse = response.getBody().getCouponOrderResponse();
                        assertThat(couponOrderResponse.getCouponId()).isEqualTo(1);
                        assertThat(couponOrderResponse.getCouponName()).isEqualTo("생일쿠폰");
                        assertThat(couponOrderResponse.getCouponSalePrice()).isEqualTo(1000);
                    },

                    () -> {
                        List<ProductOrderResponse> productOrderResponses = response.getBody().getProductOrderResponses();

                        assertThat(productOrderResponses).hasSize(4);

                        assertThat(productOrderResponses.get(0).getProductId()).isEqualTo(1);
                        assertThat(productOrderResponses.get(0).getProductName()).isEqualTo("사과");
                        assertThat(productOrderResponses.get(0).getProductPrice()).isEqualTo(1000);
                        assertThat(productOrderResponses.get(0).getOrderProductQuantity()).isEqualTo(10);

                        assertThat(productOrderResponses.get(1).getProductId()).isEqualTo(2);
                        assertThat(productOrderResponses.get(1).getProductName()).isEqualTo("배");
                        assertThat(productOrderResponses.get(1).getProductPrice()).isEqualTo(200);
                        assertThat(productOrderResponses.get(1).getOrderProductQuantity()).isEqualTo(7);

                        assertThat(productOrderResponses.get(2).getProductId()).isEqualTo(3);
                        assertThat(productOrderResponses.get(2).getProductName()).isEqualTo("귤");
                        assertThat(productOrderResponses.get(2).getProductPrice()).isEqualTo(500);
                        assertThat(productOrderResponses.get(2).getOrderProductQuantity()).isEqualTo(8);

                        assertThat(productOrderResponses.get(3).getProductId()).isEqualTo(4);
                        assertThat(productOrderResponses.get(3).getProductName()).isEqualTo("땅콩");
                        assertThat(productOrderResponses.get(3).getProductPrice()).isEqualTo(700);
                        assertThat(productOrderResponses.get(3).getOrderProductQuantity()).isEqualTo(9);

                    }
            );
        }

        @Test
        public void 주문을_쿠폰을_이용하여_결제를_실패한다_유효성_쿠폰아이디() throws Exception{
            //given
            PayProductRequest couponIdEmpty = PayProductRequest.builder()
                    .orderId(1L)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.payProductWithCouponArgumentNotValid(couponIdEmpty);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("couponId")).isEqualTo("쿠폰 아이디를 입력하세요")
            );
        }


        @Test
        public void 주문을_쿠폰을_이용하여_결제를_실패한다_유효성_주문아이디() throws Exception{
            //given
            PayProductRequest couponIdEmpty = PayProductRequest.builder()
                    .couponId(1L)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.payProductWithCouponArgumentNotValid(couponIdEmpty);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("orderId")).isEqualTo("주문 아이디를 입력하세요")
            );
        }


        @Test
        public void 주문을_쿠폰을_이용하여_결제를_실패한다_유효성_주문아이디_음수_에러() throws Exception{
            //given
            PayProductRequest couponIdEmpty = PayProductRequest.builder()
                    .couponId(1L)
                    .orderId(-1L)
                    .build();
            //when
            ApiResponse<ErrorResponse> response = couponMockApiCaller.payProductWithCouponArgumentNotValid(couponIdEmpty);

            //Then
            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(400),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("400"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("정규식에 적합하지 않습니다."),
                    () -> assertThat(response.getBody().getDetailMessage().get("orderId")).isEqualTo("주문 아이디는 양수로 입력을 하세요")
            );
        }
    }

}