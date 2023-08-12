package kr.or.funding.pyHis.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.member.DAO.IMemberDAO;
import kr.or.funding.member.DAO.MemberDAOImpl;
import kr.or.funding.pyHis.vo.PyHisVO;
import kr.or.funding.util.MyBatisUtil;

public class PyHisDaoImpl implements IPyHisDao {

	private static IPyHisDao pyHisDao;

	private SqlSession sqlSession;

	private PyHisDaoImpl() {
		sqlSession = MyBatisUtil.getInstance(true);
	}

	public static IPyHisDao getInstance() {
		if (pyHisDao == null) {
			pyHisDao = new PyHisDaoImpl();
		}
		return pyHisDao;
	}
	
	@Override
	public int insertPyHis(PyHisVO pv) {
		int cnt = sqlSession.insert("pyHis.insertPyHis",pv);
		return cnt;
	}

}
