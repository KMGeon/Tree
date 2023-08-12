package kr.or.funding.detail;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.util.MyBatisUtil;

public class DetailDAO {
	private static DetailDAO detailDao;
	
	private SqlSession sqlSession;
	
	private DetailDAO() {
		sqlSession = MyBatisUtil.getInstance(true);
	}
	public static DetailDAO getInstance() {
		if(detailDao == null) {
			detailDao = new DetailDAO();
		}
		return detailDao;
	}
}
