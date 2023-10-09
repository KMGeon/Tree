package kr.or.funding.wishlist.dao;

import java.util.List;

import kr.or.funding.wishlist.vo.WishlistVO;

public interface IWishListDao{
	
	// 찜바구니 등록
	public int insertWishList(WishlistVO wv);
	
	// 찜 목록 리스트페이지
	public List<WishlistVO> findAll();
	
	// 찜목록 페이지 이동
	
}
