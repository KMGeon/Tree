package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;

@Mapper
public interface GalleryMapper {
	// 한 권의 책에 대한 여러 개의 이미지 목록
	public BookVO list(BookVO bookVO);

	public List<BookVO> bookList();

	public int updateImg(AttachVO attachVO);

	public int deletePost(AttachVO attachVO);

	public List<BookVO> searchBook(BookVO bookVO);
	//다중 파일 업로드
	public int uploadAjaxAction(List<AttachVO> attachVOList);
	//책의 이미지인 attach테이블의 다음 seq번호 가져오기

	public int getSeq(String bookId);
}
