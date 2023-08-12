package kr.or.funding.board2.service;

import java.util.List;

import kr.or.funding.board2.dao.Notice2DAO;
import kr.or.funding.board2.vo.Notice2VO; 

public class Notice2Service  {
	private static Notice2Service Notice2Service;
	private Notice2DAO notice2Dao;
	
	private Notice2Service() {
		notice2Dao = Notice2DAO.getInstance();
	}
	
	public static Notice2Service getInstance() {
		if(Notice2Service == null) {
			Notice2Service = new Notice2Service();
		}
		return Notice2Service;
	}

	
	public int registerNotice2(Notice2VO tv) {
		int cnt = notice2Dao.insertNotice2(tv);
		return cnt;
	}

	
	public int modifyNotice2(Notice2VO tv) {
		int cnt = notice2Dao.updateNotice2(tv);
		return cnt;
	}


	public int removeNotice2(String Notice2Id) {
		int cnt = notice2Dao.deleteNotice2(Notice2Id);
		return cnt;
	}


	public Notice2VO getNotice2(String Notice2Id) {
		Notice2VO trmv = notice2Dao.getNotice2(Notice2Id);
		return trmv;
	}


	public List<Notice2VO> getAllNotice2List() {
		List<Notice2VO> Notice2List = notice2Dao.getAllNotice2List();
		return Notice2List;
	}


	public List<Notice2VO> searchList(String sch) {
		List<Notice2VO> Notice2List = notice2Dao.searchList(sch);
		return Notice2List;
	}
	
	
}
