package kr.or.funding.member;

import java.util.List;

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

	// 추가
	@Override
	public int registerMember(MemberVO mv) {
		int cnt = memDao.insertMember(mv);
		System.out.println("추가");
		return cnt;
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

//삭제
	@Override
	public int removeMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

//전체출력
	@Override
	public List<MemberVO> getAllMemberList() {
		System.out.println("지나감");
		List<MemberVO> memList = memDao.getAllMemberList();
		return memList;
	}

//선택출력
	@Override
	public List<MemberVO> searchMemberList(MemberVO mv) {
		List<MemberVO> memList = memDao.searchMemberList(mv);
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO mv = memDao.getMember(memId);
		return mv;
	}

}
