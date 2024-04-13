package dev.test.aswemake.domain.entity.coupon;

import dev.test.aswemake.domain.entity.enums.CouponSaleStrategy;
import dev.test.aswemake.domain.entity.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
    //******************************* PK 필드 *********************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /********************************* PK가 아닌 필드 *********************************/
    private String couponName;

    private int salePrice;

    @Enumerated(EnumType.STRING)
    private CouponSaleStrategy couponSaleStrategy;

    /********************************* 연관관계 매핑 *********************************/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    protected Coupon(String couponName, int salePrice, CouponSaleStrategy couponSaleStrategy, Member member) {
        this.couponName = couponName;
        this.salePrice = salePrice;
        this.couponSaleStrategy = couponSaleStrategy;
        this.member = member;
    }
}
