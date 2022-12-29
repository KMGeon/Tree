package kr.or.ddit.comm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comm.AtchFileVO;
import kr.or.ddit.util.MyBatisUtil;

public class AtchFileDAOImpl implements IAtchFileDAO {
	private static IAtchFileDAO fileDAO;
	private SqlSession sqlSession;

	private AtchFileDAOImpl() {
		sqlSession = MyBatisUtil.getInstance();
	}

	public static IAtchFileDAO getInstance() {
		if (fileDAO == null) {
			fileDAO = new AtchFileDAOImpl();
		}
		return fileDAO;

	}

	@Override
	public int insertAtchFile(SqlSession session, AtchFileVO atchFileVO) {
		int cnt = sqlSession.insert("atchFile.insertAtchFile", atchFileVO);
		if (cnt > 0) { // 성공
			String msg = "";

		}

		return cnt;
	}

	@Override
	public int insertAtchFileDetail(SqlSession session, AtchFileVO atchFileVO) {
		int cnt = session.insert("atchFile.insertAtchFile", atchFileVO);
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(SqlSession session, AtchFileVO atchFileVO) {
		List<AtchFileVO> atchFileList = session.selectList("atchFileList.getAtchFileList", atchFileVO);
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(SqlSession session, AtchFileVO atchFileVO) {
		AtchFileVO fileVo = session.selectOne("atchFileList.getAtchFileDetail", atchFileVO);
		return fileVo;
	}
}
