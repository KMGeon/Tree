package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemVO;

public interface MemService {
	//메소드 시그니처
	
 	public List<MemVO> list(Map<String,String> map);

	public int insert(MemVO memVO);

	//MEM 테이블의 전체 행 수 구함
	public int getTotal(Map<String,String> map);

	//아이디 중복체크
	public int chkDup(String memId);
 	
 	
}
