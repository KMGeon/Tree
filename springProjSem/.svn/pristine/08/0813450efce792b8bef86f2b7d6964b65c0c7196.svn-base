package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemVO;

public interface MemMapper {
	//글 목록
	public List<MemVO> memList(Map<String,String> map);
	
	public int memInsert(MemVO memVO);
	
	//MEM 테이블의 전체 행 수 구함
	//<select id="getTotal" resultType="int">
	public int getTotal(Map<String,String> map);
	
	//아이디 중복체크
	public int chkDup(String memId);
	
	//상세 보기
	public MemVO detail(String memId);
}
