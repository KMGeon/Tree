package kr.or.ddit.service;

import kr.or.ddit.vo.MemberVO;

public interface MemberService {
    public int registerCheck(String memberVO);
    public int registerMember(MemberVO memberVO);
}
