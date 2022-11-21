package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BookVO;

//매퍼 인터페이스
@Mapper
public interface BookMapper {

	// 도서 목록
	public List<BookVO> list();

	// 도서 상세(1행)
	public BookVO detail(int bookId);

	// 도서 수정
	public int updatePost(BookVO bookVO);

	// 도서 입력
	public int insertPost(BookVO bookVO);
}
