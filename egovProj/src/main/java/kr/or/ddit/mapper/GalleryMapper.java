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
	public int deletPost(AttachVO attachVO);
}
