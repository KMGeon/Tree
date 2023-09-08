package dev.test.aswemake.domain.controller;

import dev.test.aswemake.config.ControllerTest;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateListRequest;
import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.ErrorResponse;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class OrderControllerTest extends ControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        super.setup();
    }

    @AfterEach
    void cleanUp() {
        super.cleanup();
    }


    @DisplayName("GET /api/order 주문 정보 조회하기")
    @Nested
    class getOrderInfo{

        private OrderPayInfoResponse orderPayInfoResponse;

        @BeforeEach
        void setUp(){
            List<ProductOrderResponse> productOrderResponses = new ArrayList<>();

            ProductOrderResponse productOrder1 = new ProductOrderResponse(1L, "사과", 5000, 4);
            ProductOrderResponse productOrder2 = new ProductOrderResponse(2L, "배", 2000, 2);
            ProductOrderResponse productOrder3 = new ProductOrderResponse(3L, "귤", 1000, 4);
            ProductOrderResponse productOrder4 = new ProductOrderResponse(4L, "땅콩", 2000, 1);

            productOrderResponses.add(productOrder1);
            productOrderResponses.add(productOrder2);
            productOrderResponses.add(productOrder3);
            productOrderResponses.add(productOrder4);

            orderPayInfoResponse = OrderPayInfoResponse.builder()
                    .productOrderResponses(productOrderResponses)
                    .totalCost(30000)
                    .productStrategy(ProductStrategy.TOTAL)
                    .build();
        }

        @Test
        public void 회원_주문목록_조회_성공() throws Exception{

            given(orderService.getOrderInfo(any(LoginUserDto.class))).willReturn(orderPayInfoResponse);
            ApiResponse<OrderPayInfoResponse> response = orderMockApiCaller.getOrderInfoValid();


            // 상품 목록 검증
            List<ProductOrderResponse> actualProducts = response.getBody().getProductOrderResponses();
            assertThat(actualProducts).hasSize(4);

            // 응답 데이터 검증
            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(response.getBody().getTotalCost()).isEqualTo(30000);
            assertThat(response.getBody().getProductStrategy()).isEqualTo(ProductStrategy.TOTAL);


            // 각 상품별 검증
            assertAll(
                    () -> assertThat(actualProducts.get(0).getProductId()).isEqualTo(1L),
                    () -> assertThat(actualProducts.get(0).getProductName()).isEqualTo("사과"),
                    () -> assertThat(actualProducts.get(0).getProductPrice()).isEqualTo(5000),
                    () -> assertThat(actualProducts.get(0).getOrderProductQuantity()).isEqualTo(4)
            );

            assertAll(
                    () -> assertThat(actualProducts.get(1).getProductId()).isEqualTo(2L),
                    () -> assertThat(actualProducts.get(1).getProductName()).isEqualTo("배"),
                    () -> assertThat(actualProducts.get(1).getProductPrice()).isEqualTo(2000),
                    () -> assertThat(actualProducts.get(1).getOrderProductQuantity()).isEqualTo(2)
            );

            assertAll(
                    () -> assertThat(actualProducts.get(2).getProductId()).isEqualTo(3L),
                    () -> assertThat(actualProducts.get(2).getProductName()).isEqualTo("귤"),
                    () -> assertThat(actualProducts.get(2).getProductPrice()).isEqualTo(1000),
                    () -> assertThat(actualProducts.get(2).getOrderProductQuantity()).isEqualTo(4)
            );

            assertAll(
                    () -> assertThat(actualProducts.get(3).getProductId()).isEqualTo(4L),
                    () -> assertThat(actualProducts.get(3).getProductName()).isEqualTo("땅콩"),
                    () -> assertThat(actualProducts.get(3).getProductPrice()).isEqualTo(2000),
                    () -> assertThat(actualProducts.get(3).getOrderProductQuantity()).isEqualTo(1)
            );

        }

        @Test
        public void 회원_주문목록_조회_실패() throws Exception{
            ApiResponse<ErrorResponse> response = orderMockApiCaller.getOrderInfoInvalid();

            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(403),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("403"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("Access Denied: Access is denied"),
                    () -> assertThat(response.getBody().getDetailMessage().get("Spring Security")).isEqualTo("권한이 일치하지 않습니다.")
            );
        }
    }


    @DisplayName("POST /api/order 주문 하기")
    @Nested
    class createOrder{

        private OrderCreateRequest orderCreateRequest;

        @BeforeEach
        void setUp(){
            List<OrderCreateListRequest> orderCreateList = new ArrayList<>();
            OrderCreateListRequest order1 = new OrderCreateListRequest(1L, 10);
            OrderCreateListRequest order2 = new OrderCreateListRequest(2L, 10);
            OrderCreateListRequest order3 = new OrderCreateListRequest(3L, 10);
            orderCreateList.add(order1);
            orderCreateList.add(order2);
            orderCreateList.add(order3);

             orderCreateRequest = OrderCreateRequest.builder()
                    .orderCreateListRequests(orderCreateList)
                    .build();
        }

        @Test
        public void 관리자_주문_생성하기_성공() throws Exception {
            //given
            orderMockApiCaller.createOrderWithRoleMarket(orderCreateRequest);

            assertThat(orderRepository.count()).isNotNull();
        }

        @Test
        public void 회원_주문_생성하기_성공() throws Exception {
            //given
            orderMockApiCaller.createOrderWithRoleUSER(orderCreateRequest);

            assertThat(orderRepository.count()).isNotNull();
        }


        @Test
        public void 관리자_주문_생성하기_실패_Header_없음() throws Exception{

            ApiResponse<ErrorResponse> response = orderMockApiCaller.createOrderInvalidWithEmptyToken(orderCreateRequest);

            assertAll(
                    () -> assertThat(response.getStatus()).isEqualTo(403),
                    () -> assertThat(response.getBody().getCode()).isEqualTo("403"),
                    () -> assertThat(response.getBody().getMessage()).isEqualTo("Access Denied: Access is denied"),
                    () -> assertThat(response.getBody().getDetailMessage().get("Spring Security")).isEqualTo("권한이 일치하지 않습니다.")
            );
        }

    }

}