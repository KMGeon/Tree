package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface BoardService {

	public List<BoardVO> list(String keyword);

	public int insert(BoardVO boardVO);
	
}
