package kr.or.funding.oneIq;

import java.util.List;
import kr.or.funding.oneIq.OneiqVO;

public interface IOneIqService {

	
	// 회원번호으로 상세페이지
	public OneiqVO getOneiq(String mbsNum);
	
	
	//목록 조회
	public List<OneiqVO> findAll();
	
	// 글 등록 db 등록
	public int registerOneiq(OneiqVO mv);
	
	// 글 수정 db 업데이트
	public int modifyOneiq(OneiqVO mv);
	
	
	// 글 삭제 db
	public int removeOneiq(String iqNum);
	
	// 답글 달기
	public int replayModifyOneiq(OneiqVO mv);
	
	
	public int replayDeleteOneiq(OneiqVO mv);
}
