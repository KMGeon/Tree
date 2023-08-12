package kr.or.funding.ptImfor;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.util.MyBatisUtil;

public class PtImforDAOImpl implements IPtImforDAO{

	private static IPtImforDAO ptimforDao;
	
	private SqlSession sqlSession;
	
	private PtImforDAOImpl() {
		sqlSession = MyBatisUtil.getInstance(true);
		}
	
	public static IPtImforDAO getInstance() {
		if(ptimforDao == null) {
			ptimforDao = new PtImforDAOImpl();
		}
		return ptimforDao;
		
	}
	
	@Override
	public List<PtImforVO> findall(){
		return sqlSession.selectList("ptimfor.ptimforAllList");
	}
	@Override
	public List<PtImforVO> acthAll(){
		return sqlSession.selectList("ptimfor.atchList");
	}
	
	
	@Override
	public int insertPtRegister(PtImforVO pv) {
//		System.out.println("=======================");
//		System.out.println(pv.getPtNm());
//		System.out.println("=======================");
		int cnt = sqlSession.insert("ptimfor.insertPtRegister", pv);
		return cnt;
	}

	@Override
	public List<PtImforVO> selectDetail(PtImforVO pv) {
		List<PtImforVO> selectDetail = sqlSession.selectList("ptimfor.atchListDetail",pv);
		return selectDetail;
	}
}


