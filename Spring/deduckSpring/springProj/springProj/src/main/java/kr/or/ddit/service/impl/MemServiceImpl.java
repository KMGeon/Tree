package kr.or.ddit.service.impl;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dao.MemDao;
import kr.or.ddit.service.MemService;
import kr.or.ddit.util.FileUploadUtil;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemVO;

@Service
public class MemServiceImpl implements MemService {
	
	@Autowired
	MemDao memDao;

	@Inject
	FileUploadUtil fileUploadUtil;
	
	@Override
	public List<MemVO> list(Map<String,String> map) {
		return this.memDao.list(map);
	}
	
	//프링아 트랜잭션 처리를 해주렴
	@Transactional
	@Override
	public int insert(MemVO memVO) {
		//MEM 테이블에 insert
		this.memDao.insert(memVO);
		
		//FileUploadUtil활용->업로드, ATTACH테이블에 insert
		return this.fileUploadUtil.fileUploadAction(
				memVO.getPictureArray(), 
				memVO.getMemId());
	}
	
	//MEM 테이블의 전체 행 수 구함
	@Override
	public int getTotal(Map<String,String> map) {
		return this.memDao.getTotal(map);
	}
	
	//아이디 중복체크
	@Override
	public int chkDup(String memId) {
		return this.memDao.chkDup(memId);
	}
}










