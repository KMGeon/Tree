package dev.test.aswemake.domain.controller.apiCaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dev.test.aswemake.config.MockApiCaller;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.product.ProductCreateRequest;
import dev.test.aswemake.domain.controller.dto.request.product.ProductUpdateRequest;
import dev.test.aswemake.domain.controller.dto.response.product.ProductTimeResponse;
import dev.test.aswemake.global.exception.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static dev.test.aswemake.config.ControllerTest.MARKET_TOKEN;

public class ProductMockApiCaller extends MockApiCaller {

    public ProductMockApiCaller(MockMvc mockMvc, ObjectMapper objectMapper) {
        super(mockMvc, objectMapper);

    }


    public ApiResponse<ErrorResponse> createProductArgumentNotValid(ProductCreateRequest productCreateRequest) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(productCreateRequest));

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

    public ApiResponse<ProductTimeResponse> getProductPriceAtTimestamp() throws Exception {
        Long id = 1L;
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/product/{productId}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .param("timestamp", "2023-09-07 15:30:00");

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        ProductTimeResponse productTimeResponse = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), ProductTimeResponse.class);

        return new ApiResponse<>(response.getStatus(), productTimeResponse);
    }


    public ApiResponse<Integer> createProduct(ProductCreateRequest productCreateRequest) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(productCreateRequest));

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        return new ApiResponse<>(response.getStatus(), null);
    }


    public ApiResponse<Integer> updateProduct(ProductUpdateRequest productUpdateRequest, Long id) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/product/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(productUpdateRequest));

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        return new ApiResponse<>(response.getStatus(), null);
    }


    public ApiResponse<Integer> deleteProduct(Long id) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/api/product/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN);

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        return new ApiResponse<>(response.getStatus(), null);
    }

    public ApiResponse<ErrorResponse> updateProductArgumentNotValid(ProductUpdateRequest productUpdateRequest, Long id) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/product/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + MARKET_TOKEN)
                .content(objectMapper.writeValueAsBytes(productUpdateRequest));

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
