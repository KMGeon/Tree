package com.example.coupon.domain.coupon.repository;

import com.example.coupon.domain.coupon.domain.Coupon;
import com.example.coupon.domain.coupon.domain.QCoupon;
import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import com.example.coupon.domain.coupon.dto.response.QCouponResponse;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.coupon.domain.coupon.domain.QCoupon.coupon;

public class CouponRepositoryImpl implements CouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CouponRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<CouponResponse> searchPage(RequestDto conditions, Pageable pageable) {
        List<CouponResponse> content = queryFactory.select(new QCouponResponse(
                        coupon.amount
                        , coupon.name
                )).from(coupon)
                .where(
                        nameEq(conditions.getName())
                ).orderBy(coupon.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Coupon> countQuery = queryFactory
                .select(coupon)
                .from(coupon)
                .where(
                        nameEq(conditions.getName())
                );


        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression nameEq(String name) {
        return StringUtils.hasText(name) ? coupon.name.eq(name) : null;
    }

}
