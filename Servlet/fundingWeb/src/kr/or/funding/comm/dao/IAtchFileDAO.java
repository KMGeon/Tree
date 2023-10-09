package kr.or.funding.comm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.comm.vo.AtchFileVO;
import kr.or.funding.member.VO.MemberVO;

public interface IAtchFileDAO {

	/**
	 * 첨부파일 저장
	 * 
	 * @param atchFileVO
	 * @return
	 */
	public int insertAtchFile(SqlSession session, AtchFileVO atchFileVO);

	/**
	 * 첨부파일 세부정보 저장
	 * 
	 * @param atchFileVO
	 * @return
	 */
	public int insertAtchFildDetail(SqlSession session, AtchFileVO atchFileVO);

	/**
	 * 첨부파일 목록 조회하기
	 * 
	 * @param atchFileVO
	 * @return
	 */
	List<AtchFileVO> getAtchFileList(SqlSession session, AtchFileVO atchFileVO);

	public MemberVO getMember(String memId);

	/**
	 * 첨부파일 세부정보 조회하기
	 * 
	 * @param atchFileVO
	 * @return 첨부파일 세부정보를 담은 AtchFileVO객체
	 */
	public AtchFileVO getAtchFileDetail(SqlSession session, AtchFileVO atchFileVO);

}
