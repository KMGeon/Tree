package kr.or.funding.board2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.board2.vo.Notice2VO;
import kr.or.funding.util.MyBatisUtil;


public class Notice2DAO {
	private static Notice2DAO notice2Dao;
	
	private SqlSession sqlSession;
	
	private Notice2DAO() {
		sqlSession = MyBatisUtil.getInstance(true);
	}
	
	public static Notice2DAO getInstance() {
		if(notice2Dao == null) {
			notice2Dao = new Notice2DAO();
		}
		return notice2Dao;
	}
	
	
	public int insertNotice2(Notice2VO tv) {
		int cnt = sqlSession.insert("notice2.insertNotice2", tv);
		return cnt;
	}

	public int updateNotice2(Notice2VO tv) {
		int cnt = sqlSession.update("notice2.updateNotice2",tv);
		return cnt;
	}

	public int deleteNotice2(String notice2Id) {
		int cnt = sqlSession.delete("notice2.deleteNotice2", notice2Id);
		return cnt;
	}

	public Notice2VO getNotice2(String notice2Id) {
		Notice2VO notice2VO = sqlSession.selectOne("notice2.getNotice2", notice2Id);
		return notice2VO;
	}

	public List<Notice2VO> getAllNotice2List() {
		List<Notice2VO> notice2List = sqlSession.selectList("notice2.notice2AllList");
		System.out.println(notice2List);
		return notice2List;
	}

	public List<Notice2VO> searchList(String sch) {
		List<Notice2VO> notice2List = sqlSession.selectList("notice2.search",sch);
		return notice2List;
	}
	
}
