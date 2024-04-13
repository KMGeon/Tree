package dev.test.aswemake.domain.service;

import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayByIdInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayInfoResponse;
import dev.test.aswemake.global.argument.LoginUserDto;

import java.util.List;


public interface OrderService {
    OrderPayInfoResponse getOrderInfo(LoginUserDto loginUserDto);
    void createOrder(LoginUserDto loginUserDto, OrderCreateRequest orderCreateRequest);

    OrderPayByIdInfoResponse getOrderInfoByOrderId(LoginUserDto loginUserDto, Long orderId);
}
