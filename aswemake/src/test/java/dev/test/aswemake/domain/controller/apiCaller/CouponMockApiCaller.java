package dev.test.aswemake.domain.controller.apiCaller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dev.test.aswemake.config.MockApiCaller;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.PayProductRequest;
import dev.test.aswemake.domain.controller.dto.request.coupon.CouponCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.PayCouponInfoResponse;
import dev.test.aswemake.domain.controller.dto.response.coupon.CouponOrderResponse;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;
import dev.test.aswemake.domain.controller.dto.response.product.ProductOrderResponse;
import dev.test.aswemake.global.exception.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static dev.test.aswemake.config.ControllerTest.MARKET_TOKEN;
import static dev.test.aswemake.config.ControllerTest.USER_TOKEN;

public class CouponMockApiCaller extends MockApiCaller {

    public CouponMockApiCaller(MockMvc mockMvc, ObjectMapper objectMapper) {
        super(mockMvc, objectMapper);

    }
    public ApiResponse<Integer> createCoupon(CouponCreateRequest couponCreateRequest) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(couponCreateRequest));

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        return new ApiResponse<>(response.getStatus(), null);
    }

    public ApiResponse<PayCouponInfoResponse> payProductWithCoupon(PayProductRequest payProductRequest) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/coupon/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(payProductRequest));

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        String contentAsString = response.getContentAsString(StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(contentAsString);

        int totalCost = jsonNode.get("totalCost").asInt();

        JsonNode couponOrderResponseNode = jsonNode.get("couponOrderResponse");
        CouponOrderResponse couponOrderResponse = objectMapper.readValue(couponOrderResponseNode.toString(), CouponOrderResponse.class);

        List<ProductOrderResponse> productOrderResponses = objectMapper.readValue(
                jsonNode.get("productOrderResponses").toString(),
                new TypeReference<>() {});

        PayCouponInfoResponse payCouponInfoResponse = PayCouponInfoResponse.builder()
                .productOrderResponses(productOrderResponses)
                .totalCost(totalCost)
                .couponOrderResponse(couponOrderResponse)
                .build();

        return new ApiResponse<>(response.getStatus(), payCouponInfoResponse);
    }


    public ApiResponse<ErrorResponse> payProductWithCouponArgumentNotValid(PayProductRequest payProductRequest) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/coupon/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(payProductRequest));

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


    public ApiResponse<ErrorResponse> createCouponAccessDeniedException(CouponCreateRequest couponCreateRequest) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + USER_TOKEN)
                .content(objectMapper.writeValueAsBytes(couponCreateRequest));

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

    public ApiResponse<ErrorResponse> createCouponArgumentNotValid(CouponCreateRequest couponCreateRequest) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(couponCreateRequest));

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
