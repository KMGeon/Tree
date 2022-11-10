package kr.or.ddit.service;

import kr.or.ddit.vo.MemberVO;

public interface MemberService {
	//회원 로그인 확인
	public MemberVO read(String memId);
}
