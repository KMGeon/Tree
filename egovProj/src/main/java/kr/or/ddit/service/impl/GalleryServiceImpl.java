package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.GalleryMapper;
import kr.or.ddit.service.GalleryService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;

@Service
public class GalleryServiceImpl implements GalleryService {
	@Autowired
	GalleryMapper galleryMapper;

	public BookVO list(BookVO bookVO) {

		return this.galleryMapper.list(bookVO);
	}

	@Override
	public List<BookVO> bookList() {
		return this.galleryMapper.bookList();
	}

	@Override
	public int updateImg(AttachVO attachVO) {
		return this.galleryMapper.updateImg(attachVO);
	}
	
	@Override
	public int deletePost(AttachVO attachVO) {
		return this.galleryMapper.deletePost(attachVO);
	}

	@Override
	public List<BookVO> searchBook(BookVO bookVO){
		return this.galleryMapper.searchBook(bookVO);
	}
	
	@Override
	public int uploadAjaxAction(List<AttachVO> attachVOList) {
		return this.galleryMapper.uploadAjaxAction(attachVOList);
	}
	
	@Override
	public int getSeq (String bookId) {
		return this.galleryMapper.getSeq(bookId);
	}
	
}
