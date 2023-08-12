package kr.or.funding.wishlist.service;

import java.util.List;

import kr.or.funding.wishlist.vo.WishlistVO;

public interface IWishListService {

	
	// 찜목록 페이지 등록
	public int insertWishList(WishlistVO wv);
	
	// 찜목록 리스트 조회
	public List<WishlistVO> findAll();
}
