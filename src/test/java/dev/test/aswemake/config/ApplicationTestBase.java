package dev.test.aswemake.config;

import dev.test.aswemake.domain.repository.*;
import dev.test.aswemake.domain.service.*;
import dev.test.aswemake.global.jwt.util.JwtTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
public class ApplicationTestBase {
    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected JwtTokenizer jwtTokenizer;
    @Autowired
    protected MemberService memberService;
    @Autowired
    protected PriceHistoryRepository priceHistoryRepository;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected PriceHistoryService priceHistoryService;
    @Autowired
    protected ProductService productService;
    @Autowired
    protected OrderItemService orderItemService;
    @Autowired
    protected OrderService orderService;
    @Autowired
    protected OrderItemRepository orderItemRepository;
}
