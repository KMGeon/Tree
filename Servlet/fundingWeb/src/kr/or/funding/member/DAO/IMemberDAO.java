package kr.or.funding.member.DAO;

import java.util.List;

import kr.or.funding.member.VO.MemberVO;

public interface IMemberDAO {

	public int insertMember(MemberVO mv);

	public int selInsertMemeber(MemberVO mv);

	public boolean checkMember(String memId);

	public MemberVO getMember(String memId);

	public int updateMember(MemberVO mv);

	public int deleteMember(MemberVO mv);

	public List<MemberVO> getAllMemberList();

	public MemberVO login(MemberVO mv);

	//판매자 승인
	public int agreeSeller(MemberVO mv);

}
