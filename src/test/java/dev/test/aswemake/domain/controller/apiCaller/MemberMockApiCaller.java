package dev.test.aswemake.domain.controller.apiCaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dev.test.aswemake.config.MockApiCaller;
import dev.test.aswemake.domain.controller.dto.ApiResponse;
import dev.test.aswemake.domain.controller.dto.request.member.MemberLoginRequest;
import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.controller.dto.response.member.MemberLoginResponse;
import dev.test.aswemake.global.exception.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

public class MemberMockApiCaller extends MockApiCaller {

    public MemberMockApiCaller(MockMvc mockMvc, ObjectMapper objectMapper) {
        super(mockMvc, objectMapper);

    }

    public void signup(MemberSignupRequest memberSignupRequest) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(memberSignupRequest));

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();
    }

    public ApiResponse<ErrorResponse> signupFail(MemberSignupRequest memberSignupRequest) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(memberSignupRequest));

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

    public ApiResponse<MemberLoginResponse> login(MemberLoginRequest memberLoginRequest) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(memberLoginRequest));

        MockHttpServletResponse response = mockMvc.perform(builder)
                .andReturn()
                .getResponse();

        MemberLoginResponse memberLoginResponse = MemberLoginResponse.builder()
                .accessToken(JsonPath.read(response.getContentAsString(), "$.accessToken"))
                .refreshToken(JsonPath.read(response.getContentAsString(), "$.refreshToken"))
                .build();

        return new ApiResponse<>(response.getStatus(), memberLoginResponse);
    }

    public ApiResponse<ErrorResponse> loginFail(MemberLoginRequest memberLoginRequest) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(memberLoginRequest));

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
