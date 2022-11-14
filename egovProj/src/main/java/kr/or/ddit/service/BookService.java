package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BookVO;

public interface BookService {
	// 메서드 시그니처
	// 도서 목록
	public List<BookVO> list();

	public List<BookVO> detail(int bookId);

	public int update(BookVO bookVO);

	public int insert(BookVO bookVO);

}
