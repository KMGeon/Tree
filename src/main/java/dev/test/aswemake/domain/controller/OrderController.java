package dev.test.aswemake.domain.controller;

import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayByIdInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayInfoResponse;
import dev.test.aswemake.domain.service.OrderService;
import dev.test.aswemake.global.argument.IfLogin;
import dev.test.aswemake.global.argument.LoginUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MARKET')")
    public void createOrder(@IfLogin LoginUserDto loginUserDto
            , @RequestBody OrderCreateRequest orderCreateRequest) {
        orderService.createOrder(loginUserDto, orderCreateRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MARKET')")
    public OrderPayInfoResponse getOrderInfo(@IfLogin LoginUserDto loginUserDto) {
        return orderService.getOrderInfo(loginUserDto);
    }

    @GetMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MARKET')")
    public OrderPayByIdInfoResponse getOrderInfoByOrderId(@IfLogin LoginUserDto loginUserDto,
                                                          @PathVariable Long orderId) {
        return orderService.getOrderInfoByOrderId(loginUserDto, orderId);
    }
}
