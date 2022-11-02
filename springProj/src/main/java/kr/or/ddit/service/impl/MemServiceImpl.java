package kr.or.ddit.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.MemDao;
import kr.or.ddit.service.MemService;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemVO;

@Service
public class MemServiceImpl implements MemService {
	
	@Autowired
	MemDao memDao;

	@Override
	public List<MemVO> list(Map<String,String> map) {
		return this.memDao.list(map);
	}
	
	@Override
	public int insert(MemVO memVO) {
		return this.memDao.insert(memVO);
	}
	
	//MEM 테이블의 전체 행 수 구함
	@Override
	public int getTotal(Map<String,String> map) {
		return this.memDao.getTotal(map);
	}
}










