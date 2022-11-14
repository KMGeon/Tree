package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BookVO;

@Mapper
public interface BookMapper {
	
	//도서 목록
	public List<BookVO> list();
	//상세
	public List<BookVO>detail(int bookId);
	//업데이트
	public int update(BookVO bookVO);
	//추가
	public int insert(BookVO bookVO);
	
}
