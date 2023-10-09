package kr.or.ddit.comm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.comm.AtchFileVO;

public interface IAtchFileService {
//	3개를 구현해야된다.
	
//	첨부파일 목록을 저장하기 위한 메서드 , req Part정보를 꺼내기 위한 요청객체  / return : 첨부파일정보를 저장한 vo객체
	public AtchFileVO saveAtchFileList(HttpServletRequest req);
	
//	첨부파일 목록 조회
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	
//	첨부파일 세부정보 조회
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);
	

}
