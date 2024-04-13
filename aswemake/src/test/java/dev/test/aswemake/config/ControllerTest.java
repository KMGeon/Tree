package dev.test.aswemake.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.test.aswemake.domain.controller.apiCaller.CouponMockApiCaller;
import dev.test.aswemake.domain.controller.apiCaller.MemberMockApiCaller;
import dev.test.aswemake.domain.controller.apiCaller.OrderMockApiCaller;
import dev.test.aswemake.domain.controller.apiCaller.ProductMockApiCaller;
import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.domain.repository.CouponRepository;
import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.repository.OrderRepository;
import dev.test.aswemake.domain.repository.ProductRepository;
import dev.test.aswemake.domain.service.CouponService;
import dev.test.aswemake.domain.service.MemberService;
import dev.test.aswemake.domain.service.OrderService;
import dev.test.aswemake.domain.service.ProductService;
import dev.test.aswemake.enums.MemberTestEnum;
import dev.test.aswemake.global.jwt.util.JwtTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected JwtTokenizer jwtTokenizer;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected CouponService couponService;

    @MockBean
    protected ProductService productService;

    protected MemberMockApiCaller memberMockApiCaller;
    protected CouponMockApiCaller couponMockApiCaller;
    protected OrderMockApiCaller orderMockApiCaller;
    protected ProductMockApiCaller productMockApiCaller;

    public static String USER_TOKEN = null;
    public static String MARKET_TOKEN = null;
    public static String INVALID_TOKEN = null;


    protected void setup() throws Exception {
        memberMockApiCaller = new MemberMockApiCaller(mockMvc, objectMapper);
        orderMockApiCaller = new OrderMockApiCaller(mockMvc, objectMapper);
        couponMockApiCaller = new CouponMockApiCaller(mockMvc, objectMapper);
        productMockApiCaller = new ProductMockApiCaller(mockMvc, objectMapper);

        MARKET_TOKEN = jwtTokenizer.createAccessToken(1L, MemberTestEnum.MARKET_EMAIL.getMessage(), List.of(RoleEnum.MARKET.getRoleName()));
        USER_TOKEN = jwtTokenizer.createAccessToken(1L, MemberTestEnum.VALID_EMAIL.getMessage(), List.of(RoleEnum.USER.getRoleName()));
        INVALID_TOKEN = jwtTokenizer.createAccessToken(2L, MemberTestEnum.VALID_EMAIL.getMessage(), List.of("INVALID_ROLE"));
    }

    protected void cleanup() {
//        memberRepository.deleteAll();
    }

}

