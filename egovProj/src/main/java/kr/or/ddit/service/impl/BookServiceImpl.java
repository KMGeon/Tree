package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BookMapper;
import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper bookMapper;
	
	//도서 목록
	@Override
	public List<BookVO> list() {
		return this.bookMapper.list();
	}
	
	//도서 상세(1행)
	@Override
	public BookVO detail(int bookId) {
		return this.bookMapper.detail(bookId);
	}
	
	//도서 수정
	@Override
	public int updatePost(BookVO bookVO) {
		//merge into 사용
		int result = this.bookMapper.insertPost(bookVO);
		log.info("after bookVO"+bookVO);
		return this.bookMapper.insertPost(bookVO);
	}
	
	//도서 입력
	@Override
	public int insertPost(BookVO bookVO) {
		return this.bookMapper.insertPost(bookVO);
	}
}









