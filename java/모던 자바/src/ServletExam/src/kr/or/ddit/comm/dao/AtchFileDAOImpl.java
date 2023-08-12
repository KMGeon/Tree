package kr.or.ddit.comm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.util.MyBatisUtil;

public class AtchFileDAOImpl implements IAtchFileDAO{
	
	private static IAtchFileDAO fileDAO;
	
	private AtchFileDAOImpl() {
		
	}
	
	public static IAtchFileDAO getInstance() {
		if(fileDAO == null) {
			fileDAO = new AtchFileDAOImpl();
		}
		
		return fileDAO;
	}
	
	@Override
	public int insertAtchFile(SqlSession session, AtchFileVO atchFileVO) {
		
		int cnt = session.insert("atchFile.insertAtchFile", atchFileVO);
		
		return cnt;
	}

	@Override
	public int insertAtchFileDetail(SqlSession session, AtchFileVO atchFileVO) {
		
		int cnt = session.insert("atchFile.insertAtchFileDetail", atchFileVO);
		
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(SqlSession session, AtchFileVO atchFileVO) {
		
		List<AtchFileVO> atchFileList = session.selectList("atchFile.getAtchFileList", atchFileVO);
		
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(SqlSession session, AtchFileVO atchFileVO) {
		
		AtchFileVO fileVO = session.selectOne("atchFile.getAtchFileDetail", atchFileVO);
		
		return fileVO;
	}

}
