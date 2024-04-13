package dev.test.aswemake.domain.service.order;

import dev.test.aswemake.config.ApplicationTestBase;
import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateListRequest;
import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayByIdInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayInfoResponse;
import dev.test.aswemake.domain.entity.enums.OrderStatus;
import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.global.argument.LoginUserDto;
import dev.test.aswemake.global.exception.order.NotFoundOrderId;
import dev.test.aswemake.global.exception.product.NotFoundProductId;
import dev.test.aswemake.global.exception.product.NotFullYetAboutQuantity;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


@Transactional
class OrderServiceImplTest extends ApplicationTestBase {

    @Test
    public void 주문_생성하기_성공() throws Exception {
        //given

        List<OrderCreateListRequest> orderCreateListRequests = new ArrayList<>();

        orderCreateListRequests.add(OrderCreateListRequest.builder()
                .itemId(1L)
                .count(2)
                .build());
        orderCreateListRequests.add(OrderCreateListRequest.builder()
                .itemId(2L)
                .count(3)
                .build());
        orderCreateListRequests.add(OrderCreateListRequest.builder()
                .itemId(3L)
                .count(4)
                .build());

        OrderCreateRequest request = OrderCreateRequest.builder()
                .orderCreateListRequests(orderCreateListRequests)
                .build();

        //when
        orderService.createOrder(LoginUserDto.builder()
                .memberId(1L)
                .roles(List.of(RoleEnum.MARKET.getRoleName()))
                .build(), request);
        //Then

        //-> Order 생성
        List<Order> response = orderRepository.findAll();
        for (Order order : response) {
            assertAll(
                    () -> assertThat(order.getTotalCost()).isEqualTo(28000),
                    () -> assertThat(order.getId()).isEqualTo(1),
                    () -> assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.WAITING),
                    () -> assertThat(order.getDeliveryFee()).isEqualTo(5000)
            );
        }

        // -> OrderItem 생성
        long count = orderItemRepository.count();
        assertThat(count).isEqualTo(3);
    }

    @Test
    public void 주문_생성하기_실패_Request에_아이템ID_찾을_수_없음() throws Exception {
        //given
        List<OrderCreateListRequest> orderCreateListRequests = new ArrayList<>();
        orderCreateListRequests.add(OrderCreateListRequest.builder()
                .itemId(9999L)
                .count(2)
                .build());

        //when

        //Then
        assertThatThrownBy(() -> orderService.createOrder(LoginUserDto.builder()
                .memberId(1L)
                .roles(List.of(RoleEnum.MARKET.getRoleName()))
                .build(), OrderCreateRequest.builder()
                .orderCreateListRequests(orderCreateListRequests)
                .build()))
                .isInstanceOf(NotFoundProductId.class)
                .hasMessage("상품의 ID를 찾을 수 없습니다. : 9999");
    }


    @Test
    public void 상품1을_주문을_9999개를_주문을_하면_에러가_발생을_합니다() {
        //given
        List<OrderCreateListRequest> orderCreateListRequests = new ArrayList<>();
        orderCreateListRequests.add(OrderCreateListRequest.builder()
                .itemId(1L)
                .count(9999)
                .build());
        //when
        //Then
        assertThatThrownBy(() -> orderService.createOrder(LoginUserDto.builder()
                .memberId(1L)
                .roles(List.of(RoleEnum.MARKET.getRoleName()))
                .build(), OrderCreateRequest.builder()
                .orderCreateListRequests(orderCreateListRequests)
                .build()))
                .isInstanceOf(NotFullYetAboutQuantity.class)
                .hasMessage("수량이 충분하지 않습니다.9999");
    }

    @DisplayName("단일 주문 조회하기")
    @Nested
    class getOrderInfoByOrderId {

        @BeforeEach
        void setUp() {
            List<OrderCreateListRequest> orderCreateListRequests = new ArrayList<>();

            orderCreateListRequests.add(OrderCreateListRequest.builder()
                    .itemId(1L)
                    .count(2)
                    .build());
            orderCreateListRequests.add(OrderCreateListRequest.builder()
                    .itemId(2L)
                    .count(3)
                    .build());
            orderCreateListRequests.add(OrderCreateListRequest.builder()
                    .itemId(3L)
                    .count(4)
                    .build());

            OrderCreateRequest request = OrderCreateRequest.builder()
                    .orderCreateListRequests(orderCreateListRequests)
                    .build();

            //when
            orderService.createOrder(LoginUserDto.builder()
                    .memberId(1L)
                    .roles(List.of(RoleEnum.MARKET.getRoleName()))
                    .build(), request);
        }

        @Test
        @org.junit.jupiter.api.Order(1)
        public void 단일_주문_조회하기() throws Exception {
            //given
            LoginUserDto loginUserDto = LoginUserDto.builder()
                    .memberId(1L)
                    .roles(List.of(RoleEnum.MARKET.getRoleName()))
                    .build();

            Long orderId = 1L;
            //when
            OrderPayByIdInfoResponse response = orderService.getOrderInfoByOrderId(loginUserDto, orderId);
            //Then
            assertThat(response.getTotalCost()).isEqualTo(33000);
            assertThat(response.getProductStrategy()).isEqualTo(ProductStrategy.SPECIFIC);

            assertThat(response.getProductOrderResponses().get(0).getProductId()).isEqualTo(1L);
            assertThat(response.getProductOrderResponses().get(0).getProductPrice()).isEqualTo(1000);
            assertThat(response.getProductOrderResponses().get(0).getProductName()).isEqualTo("사과");
            assertThat(response.getProductOrderResponses().get(0).getOrderProductQuantity()).isEqualTo(2);

            assertThat(response.getProductOrderResponses().get(1).getProductId()).isEqualTo(2L);
            assertThat(response.getProductOrderResponses().get(1).getProductPrice()).isEqualTo(2000);
            assertThat(response.getProductOrderResponses().get(1).getProductName()).isEqualTo("바나나");
            assertThat(response.getProductOrderResponses().get(1).getOrderProductQuantity()).isEqualTo(3);

            assertThat(response.getProductOrderResponses().get(2).getProductId()).isEqualTo(3L);
            assertThat(response.getProductOrderResponses().get(2).getProductPrice()).isEqualTo(5000);
            assertThat(response.getProductOrderResponses().get(2).getProductName()).isEqualTo("책상");
            assertThat(response.getProductOrderResponses().get(2).getOrderProductQuantity()).isEqualTo(4);
        }

        @Test
        public void 단일_주문_조회하기_실패_ID가_범위를_넘음() {
            //given
            LoginUserDto loginUserDto = LoginUserDto.builder()
                    .memberId(1L)
                    .roles(List.of(RoleEnum.MARKET.getRoleName()))
                    .build();

            Long orderId = 1000L;
            //when
            //Then
            assertThatThrownBy(() -> orderService.getOrderInfoByOrderId(loginUserDto, orderId))
                    .isInstanceOf(NotFoundOrderId.class)
                    .hasMessageContaining("주문 아이디를 찾을 수 없습니다. 1000");
        }
    }

    @DisplayName("전체 주문을 조회하기")
    @Nested
    class getOrderInfo {
        @BeforeEach
        void setUp() {
            List<OrderCreateListRequest> orderCreateListRequests1 = new ArrayList<>();
            List<OrderCreateListRequest> orderCreateListRequests2 = new ArrayList<>();

            orderCreateListRequests1.add(OrderCreateListRequest.builder()
                    .itemId(1L)
                    .count(2)
                    .build());
            orderCreateListRequests1.add(OrderCreateListRequest.builder()
                    .itemId(2L)
                    .count(3)
                    .build());

            orderCreateListRequests2.add(OrderCreateListRequest.builder()
                    .itemId(2L)
                    .count(2)
                    .build());
            orderCreateListRequests2.add(OrderCreateListRequest.builder()
                    .itemId(3L)
                    .count(3)
                    .build());

            OrderCreateRequest request1 = OrderCreateRequest.builder()
                    .orderCreateListRequests(orderCreateListRequests1)
                    .build();

            OrderCreateRequest request2 = OrderCreateRequest.builder()
                    .orderCreateListRequests(orderCreateListRequests2)
                    .build();

            //when
            orderService.createOrder(LoginUserDto.builder()
                    .memberId(1L)
                    .roles(List.of(RoleEnum.MARKET.getRoleName()))
                    .build(), request1);
            orderService.createOrder(LoginUserDto.builder()
                    .memberId(1L)
                    .roles(List.of(RoleEnum.MARKET.getRoleName()))
                    .build(), request2);
        }


        @Test
        public void 전체_주문_조회하기() throws Exception {
            //given
            //when
            OrderPayInfoResponse response = orderService.getOrderInfo(LoginUserDto.builder()
                    .memberId(1L)
                    .roles(List.of(RoleEnum.MARKET.getRoleName()))
                    .build());

            //Then
            assertThat(response.getTotalCost()).isEqualTo(37000);
            assertThat(response.getProductStrategy()).isEqualTo(ProductStrategy.SPECIFIC);

            assertAll(
                    () -> assertThat(response.getProductOrderResponses().get(0).getProductId()).isEqualTo(1),
                    () -> assertThat(response.getProductOrderResponses().get(0).getProductName()).isEqualTo("사과"),
                    () -> assertThat(response.getProductOrderResponses().get(0).getOrderProductQuantity()).isEqualTo(2),
                    () -> assertThat(response.getProductOrderResponses().get(0).getProductPrice()).isEqualTo(1000),

                    () -> assertThat(response.getProductOrderResponses().get(1).getProductId()).isEqualTo(2),
                    () -> assertThat(response.getProductOrderResponses().get(1).getProductName()).isEqualTo("바나나"),
                    () -> assertThat(response.getProductOrderResponses().get(1).getOrderProductQuantity()).isEqualTo(3),
                    () -> assertThat(response.getProductOrderResponses().get(1).getProductPrice()).isEqualTo(2000),

                    () -> assertThat(response.getProductOrderResponses().get(2).getProductId()).isEqualTo(2),
                    () -> assertThat(response.getProductOrderResponses().get(2).getProductName()).isEqualTo("바나나"),
                    () -> assertThat(response.getProductOrderResponses().get(2).getOrderProductQuantity()).isEqualTo(2),
                    () -> assertThat(response.getProductOrderResponses().get(2).getProductPrice()).isEqualTo(2000),

                    () -> assertThat(response.getProductOrderResponses().get(3).getProductId()).isEqualTo(3),
                    () -> assertThat(response.getProductOrderResponses().get(3).getProductName()).isEqualTo("책상"),
                    () -> assertThat(response.getProductOrderResponses().get(3).getOrderProductQuantity()).isEqualTo(3),
                    () -> assertThat(response.getProductOrderResponses().get(3).getProductPrice()).isEqualTo(5000)
            );
        }
    }
}