package kr.or.funding.wishlist.service;

import java.util.List;

import kr.or.funding.wishlist.dao.IWishListDao;
import kr.or.funding.wishlist.dao.WishListDaoimpl;
import kr.or.funding.wishlist.vo.WishlistVO;

public class WishListServiceimpl implements IWishListService{

	private static IWishListService wishListService;

	private IWishListDao wishListDao;

	private WishListServiceimpl() {
		wishListDao = WishListDaoimpl.getInstance();
	}

	public static IWishListService getInstance() {

		if (wishListService == null) {
			wishListService = new WishListServiceimpl();
		}
		return wishListService;
	}
	
	
	@Override
	public int insertWishList(WishlistVO wv) {
		// TODO Auto-generated method stub
		int cnt= wishListDao.insertWishList(wv);
		return cnt;
	}

	
	// 찜목록 리스트 조회
	@Override
	public List<WishlistVO> findAll() {
		
		List<WishlistVO> wishListList = wishListDao.findAll();
		
		return wishListList;
	}

}
