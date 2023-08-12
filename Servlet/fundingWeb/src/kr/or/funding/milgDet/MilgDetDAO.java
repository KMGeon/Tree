package kr.or.funding.milgDet;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.util.MyBatisUtil;

public class MilgDetDAO {
	private static MilgDetDAO memDao;

	private SqlSession sqlSession;

	private MilgDetDAO() {
		sqlSession = MyBatisUtil.getInstance(true);
	}

	public static MilgDetDAO getInstance() {
		if (memDao == null) {
			memDao = new MilgDetDAO();
		}
		return memDao;
	}
}
