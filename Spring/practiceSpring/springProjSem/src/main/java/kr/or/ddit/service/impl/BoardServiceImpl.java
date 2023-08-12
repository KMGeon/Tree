package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.BoardDao;
import kr.or.ddit.service.BoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.BookVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardVO> list(String keyword) {
		return this.boardDao.list(keyword);
	}
	
	@Override
	public int insert(BoardVO boardVO) {
		return this.boardDao.insert(boardVO);
	}
}
