package dev.test.aswemake.domain.repository;

import dev.test.aswemake.domain.entity.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
