package kr.or.funding.comm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.funding.comm.vo.AtchFileVO;
import kr.or.funding.member.VO.MemberVO;

public interface IAtchFileService {

	public AtchFileVO saveAtchFileList(HttpServletRequest req);

	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);

	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);

	public MemberVO getMember(String memId);
}
