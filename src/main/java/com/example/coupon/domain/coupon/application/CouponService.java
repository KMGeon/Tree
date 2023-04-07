package com.example.coupon.domain.coupon.application;

import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.domain.Coupon;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import com.example.coupon.global.exception.NotFoundIdException;
import com.example.coupon.domain.coupon.repository.CouponRepository;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;


    public Page<CouponResponse> searchQueryDsl(RequestDto requestDto, Pageable pageable) {
        return couponRepository.searchPage(requestDto, pageable);
    }

    /**
     * 특정 쿠폰 생성
     *
     * @param requestDto
     * @return
     */
    public CouponResponse createCoupon(RequestDto requestDto) {
        Coupon coupon = requestDto.toEntity();
        return CouponResponse.convertCouponData(couponRepository.save(coupon));
    }

    /**
     * @return 모든 쿠폰을 찾아서 리턴한다.
     * @author mugeon
     */
    @Transactional(readOnly = true)
    public List<CouponResponse> getCoupons(String codeType) {
        if (codeType.equals("kor")) {
            boolean hasKorean = couponRepository.findAll().stream()
                    .map(Coupon::getName)
                    .anyMatch(name -> name.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"));

            if (hasKorean) {
                throw new NotFoundIdException(1L);
            }
        }
        return couponRepository.findByUseCouponFalse().stream()
                .map(CouponResponse::convertCouponData)
                .collect(Collectors.toList());
    }

    /**
     * 특정 쿠폰 조회
     *
     * @param id
     * @return
     * @throws NotFoundIdException
     */
    @Transactional(readOnly = true)
    public CouponResponse getCoupon(Long id) {
        Coupon coupon = couponRepository.findByIdFalse(id)
                .orElseThrow(() -> new NotFoundIdException(id));
        return CouponResponse.convertCouponData(coupon);
    }

    /**
     * 특정 쿠폰 수정
     *
     * @param id
     * @param requestDto
     * @return
     * @throws NotFoundIdException
     */
    public CouponResponse updateCoupon(Long id, RequestDto requestDto) {
        Coupon coupon = couponFindWithId(id);
        coupon.updateCoupon(requestDto);
        return CouponResponse.convertCouponData(coupon);
    }

    /**
     * 특정 쿠폰 삭제
     *
     * @param id
     * @throws NotFoundIdException
     */
    public void deleteCoupon(Long id) {
        Coupon coupon = couponFindWithId(id);
        coupon.destroy();
    }

    public Coupon couponFindWithId(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdException(id));
    }

}
