package dev.test.aswemake.global.initializer;

import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.entity.coupon.Coupon;
import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import dev.test.aswemake.domain.entity.enums.ProductStrategy;
import dev.test.aswemake.domain.entity.enums.RoleEnum;
import dev.test.aswemake.domain.entity.member.Member;
import dev.test.aswemake.domain.entity.product.PriceHistory;
import dev.test.aswemake.domain.entity.product.Product;
import dev.test.aswemake.domain.entity.role.Role;
import dev.test.aswemake.domain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class Initializer {

    @Bean
    public CommandLineRunner initRoles(
            RoleRepository roleRepository,
            MemberRepository memberRepository,
            ProductRepository productRepository,
            CouponRepository couponRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role user = Role.builder()
                        .roleId(1L)
                        .name(RoleEnum.USER.getRoleName())
                        .build();

                Role market = Role.builder()
                        .roleId(2L)
                        .name(RoleEnum.MARKET.getRoleName())
                        .build();

                roleRepository.save(user);
                roleRepository.save(market);
            }
            if (memberRepository.count() == 0) {

                Optional<Role> adminRoleOptional = roleRepository.findByName(RoleEnum.MARKET.getRoleName());
                Optional<Role> userRole = roleRepository.findByName(RoleEnum.USER.getRoleName());

                if (adminRoleOptional.isPresent()) {
                    Role adminRole = adminRoleOptional.get();

                    MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                            .email("market@market.com")
                            .password("market1234!")
                            .build();

                    Member member = Member.signupMember(memberSignupRequest, passwordEncoder);
                    member.changeRole(adminRole);
                    memberRepository.save(member);
                }
                if (userRole.isPresent()) {
                    Role role = userRole.get();

                    MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                            .email("test1234@test.com")
                            .password("test1234!")
                            .build();

                    Member member = Member.signupMember(memberSignupRequest, passwordEncoder);
                    member.changeRole(role);
                    memberRepository.save(member);
                }


            }

            Member market = memberRepository.findByEmail("market@market.com")
                    .orElseThrow();

            Member user = memberRepository.findByEmail("test1234@test.com")
                    .orElseThrow();
            if (couponRepository.count() == 0) {

                Coupon marketCoupon1 = Coupon.builder()
                        .couponName("고정 500원")
                        .salePrice(500)
                        .couponSaleStrategy(CouponSaleStrategy.FIX)
                        .member(market)
                        .build();

                Coupon marketCoupon2 = Coupon.builder()
                        .couponName("비율 20%")
                        .salePrice(20)
                        .couponSaleStrategy(CouponSaleStrategy.RATE)
                        .member(market)
                        .build();

                Coupon userCoupon1 = Coupon.builder()
                        .couponName("고정 1000원")
                        .salePrice(1000)
                        .couponSaleStrategy(CouponSaleStrategy.FIX)
                        .member(user)
                        .build();

                Coupon userCoupon2 = Coupon.builder()
                        .couponName("비율 30%")
                        .salePrice(30)
                        .couponSaleStrategy(CouponSaleStrategy.RATE)
                        .member(user)
                        .build();

                couponRepository.save(marketCoupon1);
                couponRepository.save(marketCoupon2);
                couponRepository.save(userCoupon1);
                couponRepository.save(userCoupon2);

            }


            if (productRepository.count() == 0) {

                productRepository.save(Product.builder()
                        .name("사과")
                        .price(1000)
                        .productQuantity(10)
                        .productStrategy(ProductStrategy.TOTAL)
                        .build());
                productRepository.save(Product.builder()
                        .name("바나나")
                        .price(2000)
                        .productQuantity(5)
                        .productStrategy(ProductStrategy.TOTAL)
                        .build());
                productRepository.save(Product.builder()
                        .name("책상")
                        .price(5000)
                        .productQuantity(30)
                        .productStrategy(ProductStrategy.SPECIFIC)
                        .build());
                productRepository.save(Product.builder()
                        .name("마우스")
                        .price(10000)
                        .productQuantity(30)
                        .productStrategy(ProductStrategy.SPECIFIC)
                        .build());
                productRepository.save(Product.builder()
                        .name("키보드")
                        .price(20000)
                        .productQuantity(10)
                        .productStrategy(ProductStrategy.SPECIFIC)
                        .build());
            }
        };
    }
}
