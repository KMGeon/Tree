package kr.or.ddit.mapper;

import kr.or.ddit.vo.MemberVO;

public interface MemberMapper {
	//회원로그인확인
	public MemberVO read(String memId);
}
