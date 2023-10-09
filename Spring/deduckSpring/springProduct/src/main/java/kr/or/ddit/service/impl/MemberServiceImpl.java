package kr.or.ddit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	//DI(의존성 주입)
	@Autowired
	MemberMapper memberMapper;
	
	//회원로그인확인
	@Override
	public MemberVO read(String memId) {
		return this.memberMapper.read(memId);
	}
	
}
