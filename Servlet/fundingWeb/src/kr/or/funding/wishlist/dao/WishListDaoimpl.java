package kr.or.funding.wishlist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.pyHis.dao.IPyHisDao;
import kr.or.funding.pyHis.dao.PyHisDaoImpl;
import kr.or.funding.util.MyBatisUtil;
import kr.or.funding.wishlist.vo.WishlistVO;

public class WishListDaoimpl implements IWishListDao{

	private static IWishListDao wishListDao;

	private SqlSession sqlSession;

	private WishListDaoimpl() {
		sqlSession = MyBatisUtil.getInstance(true);
	}

	public static IWishListDao getInstance() {
		if (wishListDao == null) {
			wishListDao = new WishListDaoimpl();
		}
		return wishListDao;
	}
	
	
	// 찜목록 등록
	@Override
	public int insertWishList(WishlistVO wv) {
		int cnt = sqlSession.insert("wishList.insertWishList",wv);
		return cnt;
	}

	
	// 찜목록 리스트
	@Override
	public List<WishlistVO> findAll() {
		return sqlSession.selectList("wishList.WishListlist");
	}

}
