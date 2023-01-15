package kr.or.ddit.mapper;

import kr.or.ddit.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public int registerCheck(String memberVO);
    public int registerMember(MemberVO memberVO);
}
