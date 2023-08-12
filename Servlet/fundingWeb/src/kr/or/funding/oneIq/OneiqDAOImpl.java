package kr.or.funding.oneIq;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.util.MyBatisUtil;

public class OneiqDAOImpl implements IOneIqDAO {
	
	private static IOneIqDAO oneiqDao;
	
	private SqlSession sqlSession;
	
	public OneiqDAOImpl() {
		
		sqlSession = MyBatisUtil.getInstance(true);
	}
	
	public static IOneIqDAO getinstance() {
		if(oneiqDao == null) {
			oneiqDao = new OneiqDAOImpl();
		}
		return oneiqDao;
	}
	
	@Override
	public int insertOneiq(OneiqVO mv) {
		int cnt = sqlSession.insert("oneiq.insertOneiq", mv);
		return cnt;
	}
	
	
	@Override
	public int deleteOneiq(String iqNum) {
		int cnt = sqlSession.delete("oneiq.deleteOneiq",iqNum);
		return cnt;
	}
	
	@Override
	public int updateOneiq(OneiqVO mv) {
		int cnt = sqlSession.update("oneiq.updateOneiq",mv);
		return cnt;
	}
	
	
	@Override
	public List<OneiqVO> findall(){
		return sqlSession.selectList("oneiq.oneiqList");
	}
	
	@Override
	public OneiqVO getOneiq(String mbsNum) {
		
		OneiqVO oneiqvo = sqlSession.selectOne("oneiq.getOneiq",mbsNum);
		return oneiqvo;
	}
	
	public int replyOneiq(OneiqVO mv) {
		int cnt = sqlSession.update("oneiq.replyOneiq",mv);
		return cnt;
	}
	
	public int replyOneiqDelete(OneiqVO mv) {
		int cnt = sqlSession.update("oneiq.replyOneiqDelete", mv);
		return cnt;
	}
	
	
	
	
}
