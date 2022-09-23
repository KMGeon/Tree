package kr.or.funding.member.service;

import java.lang.reflect.Member;
import java.util.List;

import kr.or.funding.member.VO.MemberVO;

public interface IMemberService {

	public int registerMember(MemberVO mv);

	public int sellerMember(MemberVO mv);

	public boolean checkMember(String memId);

	public MemberVO getMember(String memId);

	public int modifyMember(MemberVO mv);
	
	//판매자 회원가입 승인
	public int agreeSeller(MemberVO mv);

	public int removeMember(MemberVO mv);

	public List<MemberVO> getAllMemberList();

	public MemberVO login(MemberVO mv);

	


}
