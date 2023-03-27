package com.example.coupon.domain.coupon.repository;

import com.example.coupon.domain.coupon.domain.Coupon;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

  @Query("select c from Coupon c where c.useCoupon = false")
  List<Coupon> findByUseCouponFalse();

  @Query("select c from Coupon c where c.id=:id and c.useCoupon = false")
  Optional<Coupon> findByIdFalse(@Param("id") Long id);
}
