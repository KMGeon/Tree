package kr.or.funding.oneIq;

import java.util.List;

public interface IOneIqDAO {

	
	//
	
	// 등록된글 세부 정보
	public OneiqVO getOneiq(String mbsNum);
	
	// 등록하기 새글
	public int insertOneiq(OneiqVO oneiqVO);
	
	// 등록된 글 삭제
	public int deleteOneiq(String iqNum);
	
	// 등록된 글 수정
	public int updateOneiq(OneiqVO mv);
	
	// 전체 목록
	public List<OneiqVO> findall();

	public int replyOneiq(OneiqVO oneiqVO);
	

	public int replyOneiqDelete(OneiqVO mv);
}
