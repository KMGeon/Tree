package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;

public interface GalleryMapper {
	
	//한 권의 책에 대한 여러 개의 이미지 목록
	public BookVO list(BookVO bookVO);
	//도서 목록 가져와서 select에 추가하기
	public List<BookVO> bookList();
	//변경된 사진 db에 반영
	public int updatePost(AttachVO attachVO);
	//갤러리 사진 삭제
	public int deletePost(AttachVO attachVO);
	//도서 검색
	public List<BookVO> searchBook(BookVO bookVO);
	//책 별 이미지 등록
	public int uploadAjaxAction(List<AttachVO> attachVOList);
	//책의 이미지인 ATTACH 테이블의 다음 seq번호 가져오기
	public int getSeq(String bookId);
}









