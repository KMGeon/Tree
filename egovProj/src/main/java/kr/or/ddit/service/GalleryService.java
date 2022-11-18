package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;

public interface GalleryService {
	public BookVO list(BookVO bookVO);

	public List<BookVO> bookList();

	public int updateImg(AttachVO attachVO);

	public int deletePost(AttachVO attachVO);

	public List<BookVO> searchBook(BookVO bookVO);

	

	

	int uploadAjaxAction(List<AttachVO> attachVOList);

	int getSeq(String bookId);
}
