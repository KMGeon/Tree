package kr.or.ddit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.vo.BookVO;

//서비스 interface : 비즈니스 로직
@Service
public interface BookService {
	// 메소드 시그니처

	// BOOK 테이블에 insert
	public int insert(BookVO bookVO);

	BookVO select_detail(BookVO bookVO);

	public List<BookVO> list();

	public int update(BookVO bookVO);

	public int delete(int bookId);

}
