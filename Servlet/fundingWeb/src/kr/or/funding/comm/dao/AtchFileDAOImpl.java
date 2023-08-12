package kr.or.funding.comm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.comm.vo.AtchFileVO;
import kr.or.funding.member.DAO.IMemberDAO;
import kr.or.funding.member.VO.MemberVO;

public class AtchFileDAOImpl implements IAtchFileDAO {

	private static IAtchFileDAO fileDAO;
	private static IMemberDAO memDao;

	private SqlSession sqlSession;

	private AtchFileDAOImpl() {

	}

	public static IAtchFileDAO getInstance() {
		if (fileDAO == null) {
			fileDAO = new AtchFileDAOImpl();
		}
		return fileDAO;
	}

	@Override
	public MemberVO getMember(String memId) {
		// TODO Auto-generated method stub
		MemberVO memVO = sqlSession.selectOne("member.getMember", memId);

		return memVO;
	}

	@Override
	public int insertAtchFile(SqlSession session, AtchFileVO atchFileVO) {

		int cnt = session.insert("atchFile.insertAtchFile", atchFileVO);

		return cnt;
	}

	@Override
	public int insertAtchFildDetail(SqlSession session, AtchFileVO atchFileVO) {

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
