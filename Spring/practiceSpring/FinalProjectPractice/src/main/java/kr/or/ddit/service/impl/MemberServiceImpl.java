package kr.or.ddit.service.impl;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;
    public int registerCheck(String memEmail){
        return this.memberMapper.registerCheck(memEmail);
    }

    @Override
    public int registerMember(MemberVO memberVO) {
        return this.memberMapper.registerMember(memberVO);
    }
}
