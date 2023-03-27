package com.example.coupon.domain.coupon.application;

import com.example.coupon.domain.coupon.dto.request.RequestDto;
import com.example.coupon.domain.coupon.domain.Coupon;
import com.example.coupon.domain.coupon.dto.response.CouponResponse;
import com.example.coupon.domain.coupon.exception.NotFoundIdException;
import com.example.coupon.domain.coupon.repository.CouponRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService {

  private final CouponRepository couponRepository;

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
  public List<CouponResponse> getCoupons() {
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
    Coupon coupon = couponRepository.findById(id)
        .orElseThrow(() -> new NotFoundIdException(id));
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
    Coupon coupon = couponRepository.findById(id)
        .orElseThrow(() -> new NotFoundIdException(id));
    coupon.destroy();
  }


}
