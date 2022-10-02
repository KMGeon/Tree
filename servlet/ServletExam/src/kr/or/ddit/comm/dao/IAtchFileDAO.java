package kr.or.ddit.comm.dao;

import kr.or.ddit.comm.AtchFileVO;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public interface IAtchFileDAO {

	public int insertAtchFile(SqlSession session ,AtchFileVO atchFileVO);// 첨부파일 저장

	public int insertAtchFileDetail(SqlSession session,AtchFileVO atchFileVO);// 첨부파일 세부정보 저장

	List<AtchFileVO> getAtchFileList(SqlSession session,AtchFileVO atchFileVO);// 첨부파일 목록 조회하기

	public AtchFileVO getAtchFileDetail(SqlSession session,AtchFileVO atchFileVO);// 첨부파일 세부정보 조회하기

}
