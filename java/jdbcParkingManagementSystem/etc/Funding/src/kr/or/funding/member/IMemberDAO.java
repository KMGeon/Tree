package kr.or.funding.member;

import java.util.List;

public interface IMemberDAO {

	public int insertMember(MemberVO mv);

	public boolean checkMember(String memId);

	public MemberVO getMember(String memId);

	public int updateMember(MemberVO mv);

	public int deleteMember(String memId);

	public List<MemberVO> getAllMemberList();

	public List<MemberVO> searchMemberList(MemberVO mv);

}
