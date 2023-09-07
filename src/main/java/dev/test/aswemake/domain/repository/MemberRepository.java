package dev.test.aswemake.domain.repository;

import dev.test.aswemake.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("SELECT DISTINCT m, o" +
            " FROM Member m " +
            "LEFT JOIN FETCH m.orders o " +
            "WHERE m.id = :memberId")
    Optional<Member> findByIdWithOrder(@Param("memberId") Long memberId);

    @Query("SELECT DISTINCT m" +
            " FROM Member m " +
            "LEFT JOIN FETCH m.coupons c " +
            "WHERE m.id = :memberId")
    Optional<Member> findByIdWithCoupon(@Param("memberId")Long memberId);
}
