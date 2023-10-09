package kr.or.ddit.service;

import kr.or.ddit.vo.BookVO;

//서비스 interface : 비즈니스 로직
public interface BookService {
	//메소드 시그니처
	
	//BOOK 테이블에 insert
	public int insert(BookVO bookVO);
}
