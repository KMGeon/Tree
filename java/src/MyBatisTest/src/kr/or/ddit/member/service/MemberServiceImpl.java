package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.dao.MemberDAOImplForJDBC;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	
	private static IMemberService memService;
	
	private IMemberDAO memDao;
	
	private MemberServiceImpl() {
		memDao = MemberDAOImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		
		return memService;
	}
	
	@Override
	public int regisetMember(MemberVO mv) {
		int cnt = memDao.insertMember(mv);
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk = memDao.checkMember(memId);
		return chk;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public int removeMember(String memId) {
		int cnt = memDao.deleteMember(memId); 
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = memDao.getAllMemberList();
		return memList;
	}
	
	public void accountTransfer() {
		try {
		// 트랜잭션 시작
		// 계좌DAO.update(); 유저 1계정에 백만원 인출
		// 계좌DAO.update(); 유저 2계정에 백만원 입금
		// 트랜잭션 종료(commit)
		} catch (Exception e) {
			// 롤백 처리
		}
		
	}

	@Override
	public List<MemberVO> searchMemberList(MemberVO mv) {
		
		List<MemberVO> memList = memDao.searchMemberList(mv);
		
		return memList;
	}
	
	

}
