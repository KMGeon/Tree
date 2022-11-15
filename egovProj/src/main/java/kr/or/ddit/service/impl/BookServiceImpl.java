package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BookMapper;
import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper bookMapper;
	
	//도서 목록
	@Override
	public List<BookVO> list() {
		return this.bookMapper.list();
	}
	
	@Override
	public List<BookVO>detail(int bookId){
		return this.bookMapper.detail(bookId);
	}
	@Override
	public int update(BookVO bookVO) {
		return this.bookMapper.update(bookVO);
	}
	@Override
	public int insert(BookVO bookVO) {
		return this.bookMapper.insert(bookVO);
	}
	
	
}
