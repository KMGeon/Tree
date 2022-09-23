package kr.or.funding.member.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.member.VO.MemberVO;
import kr.or.funding.util.MyBatisUtil;

public class MemberDAOImpl implements IMemberDAO {
	private static IMemberDAO memDao;

	private SqlSession sqlSession;

	private MemberDAOImpl() {
		sqlSession = MyBatisUtil.getInstance(true);
	}

	public static IMemberDAO getInstance() {
		if (memDao == null) {
			memDao = new MemberDAOImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = sqlSession.insert("member.insertMember", mv);

		return cnt;
	}

	@Override
	public int agreeSeller(MemberVO mv) {
		int cnt = sqlSession.update("member.acceptSeller", mv);
		return cnt;
	}

	@Override
	public int selInsertMemeber(MemberVO mv) {
		int cnt = sqlSession.insert("member.selInsertMemeber", mv);

		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk = false;

		int cnt = sqlSession.selectOne("member.checkMember", memId);

		if (cnt > 0) {
			chk = true;
		}
		return chk;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = sqlSession.update("member.updateMember", mv);
		return cnt;
	}

	@Override
	public int deleteMember(MemberVO mv) {
		int cnt = sqlSession.delete("member.deleteMember", mv);
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = sqlSession.selectList("member.memberAllList");
		return memList;
	}

	@Override
	public MemberVO login(MemberVO mv) {
		MemberVO memList = sqlSession.selectOne("member.login", mv);
		return memList;
	}
	
	@Override
	public MemberVO getMember(String memId) {
		MemberVO memVO = sqlSession.selectOne("member.getMember", memId);

		return memVO;
	}

}
