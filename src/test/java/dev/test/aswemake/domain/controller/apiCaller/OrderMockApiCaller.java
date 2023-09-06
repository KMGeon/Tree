package dev.test.aswemake.domain.controller.apiCaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dev.test.aswemake.config.MockApiCaller;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.order.OrderCreateRequest;
import dev.test.aswemake.domain.controller.dto.response.order.OrderPayInfoResponse;
import dev.test.aswemake.global.exception.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static dev.test.aswemake.config.ControllerTest.*;


public class OrderMockApiCaller extends MockApiCaller {

    public OrderMockApiCaller(MockMvc mockMvc, ObjectMapper objectMapper) {
        super(mockMvc, objectMapper);

    }

    public void createOrderWithRoleMarket(OrderCreateRequest orderCreateRequest) throws JsonProcessingException {
        MockMvcRequestBuilders.post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(orderCreateRequest));
    }

    public void createOrderWithRoleUSER(OrderCreateRequest orderCreateRequest) throws JsonProcessingException {
        MockMvcRequestBuilders.post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + USER_TOKEN)
                .content(objectMapper.writeValueAsBytes(orderCreateRequest));
    }

    public ApiResponse<ErrorResponse> createOrderInvalidWithEmptyToken(OrderCreateRequest orderCreateRequest) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + INVALID_TOKEN)
                .content(objectMapper.writeValueAsBytes(orderCreateRequest));

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(JsonPath.read(response.getContentAsString(StandardCharsets.UTF_8), "$.code"))
                .message(JsonPath.read(response.getContentAsString(StandardCharsets.UTF_8), "$.message"))
                .detailMessage(JsonPath.read(response.getContentAsString(StandardCharsets.UTF_8), "$.detailMessage"))
                .build();

        return new ApiResponse<>(response.getStatus(), errorResponse);
    }


    public ApiResponse<OrderPayInfoResponse> getOrderInfoValid() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + USER_TOKEN);

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        OrderPayInfoResponse orderPayInfoResponse =
                objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), OrderPayInfoResponse.class);

        return new ApiResponse<>(response.getStatus(), orderPayInfoResponse);
    }


    public ApiResponse<ErrorResponse> getOrderInfoInvalid() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + INVALID_TOKEN);

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(JsonPath.read(response.getContentAsString(StandardCharsets.UTF_8), "$.code"))
                .message(JsonPath.read(response.getContentAsString(StandardCharsets.UTF_8), "$.message"))
                .detailMessage(JsonPath.read(response.getContentAsString(StandardCharsets.UTF_8), "$.detailMessage"))
                .build();

        return new ApiResponse<>(response.getStatus(), errorResponse);
    }
}
