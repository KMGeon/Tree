package kr.or.funding.acm.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.acm.vo.AcommodationVO;
import kr.or.funding.util.MyBatisUtil;

public class AcommodationDAOImpl implements IAcommodationDAO {

	private static IAcommodationDAO acmDao;

	private SqlSession sqlSession;

	private AcommodationDAOImpl() {
		sqlSession = MyBatisUtil.getInstance(true);
	}

	public static IAcommodationDAO getInstance() {
		if (acmDao == null) {
			acmDao = new AcommodationDAOImpl();
		}
		return acmDao;
	}

	@Override
	public int insert(AcommodationVO acmv) {
		int cnt = sqlSession.insert("acm.insert", acmv);
		return cnt;
	}

}