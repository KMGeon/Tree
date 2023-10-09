package kr.or.funding.member.service;

import java.util.List;

import kr.or.funding.member.DAO.IMemberDAO;
import kr.or.funding.member.DAO.MemberDAOImpl;
import kr.or.funding.member.VO.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private static IMemberService memService;

	private IMemberDAO memDao;

	private MemberServiceImpl() {
		memDao = MemberDAOImpl.getInstance();
	}

	public static IMemberService getInstance() {

		if (memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}

	// 일반추가
	@Override
	public int registerMember(MemberVO mv) {
		int cnt = memDao.insertMember(mv);
		System.out.println("일반추가");
		return cnt;
	}
	
	//판매자 회원가입 승인
	public int agreeSeller(MemberVO mv) {
		int cnt = memDao.agreeSeller(mv);
		System.out.println("판매자 회원가입 승인");
		return cnt;
	}
	
	

	// 판매자 추가
	@Override
	public int sellerMember(MemberVO mv) {
		int cnt = memDao.selInsertMemeber(mv);
		return 0;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk = memDao.checkMember(memId);
		return chk;
	}

	// 업데이트
	@Override
	public int modifyMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		return cnt;
	}


//전체출력
	@Override
	public List<MemberVO> getAllMemberList() {
		System.out.println("지나감");
		List<MemberVO> memList = memDao.getAllMemberList();
		return memList;
	}




	@Override
	public MemberVO getMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeMember(MemberVO mv) {
		int cnt = memDao.deleteMember(mv);
		return cnt;
	}

	@Override
	public MemberVO login(MemberVO mv) {
		
		return memDao.login(mv);
	}

	


	


}
