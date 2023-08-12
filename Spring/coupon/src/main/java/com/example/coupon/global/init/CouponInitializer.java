package com.example.coupon.global.init;

import com.example.coupon.domain.coupon.domain.Coupon;
import com.example.coupon.domain.coupon.repository.CouponRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouponInitializer {

    @Bean
    public CommandLineRunner initRoles(CouponRepository couponRepository) {
        return args -> {
            if (couponRepository.count() == 0) {
                for (int i = 0; i < 100; i++) {
                    Coupon coupon = Coupon.builder()
                            .amount(1000)
                            .name("쿠폰"+i)
                            .build();
                couponRepository.save(coupon);
                }

            }
        };
    }
}
