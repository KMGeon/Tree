package kr.or.funding.member;

import java.util.List;

public interface IMemberService {

	public int registerMember(MemberVO mv); 

	public boolean checkMember(String memId);

	public MemberVO getMember(String memId);

	public int modifyMember(MemberVO mv);

	public int removeMember(String memId);

	public List<MemberVO> getAllMemberList();

	public List<MemberVO> searchMemberList(MemberVO mv);
}
