package kr.or.ddit.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;

@Mapper
public interface MemberMapper {
	// 1행
	public MemberVO read(String memId);
}
