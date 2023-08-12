package kr.or.funding.oneIq;

import java.util.List;


public class OneiqServiceImpl implements IOneIqService{
	
	private static IOneIqService oneiqService;
	
	private IOneIqDAO oneiqDao;
	
	private OneiqServiceImpl() {
		
		oneiqDao = OneiqDAOImpl.getinstance();
	}
	
	public static IOneIqService getInstance() {
		
		if(oneiqService == null) {
			oneiqService = new OneiqServiceImpl();
		}
		return oneiqService;
	}
	
	
	// 등록
	@Override
	public int registerOneiq(OneiqVO mv) {
		
		int cnt = oneiqDao.insertOneiq(mv);
		
		return cnt;
	}
	

	
	// 삭제
	@Override
	public int removeOneiq(String iqNum) {
		
		int cnt = oneiqDao.deleteOneiq(iqNum);
		return cnt;
	}
	
	
	// 수정
	@Override
	public int modifyOneiq(OneiqVO mv) {
		
		int cnt = oneiqDao.updateOneiq(mv);
		return cnt;
	}
	
	// 상세페이지 부분?
	@Override
	public OneiqVO getOneiq(String mbsNum) {
		
		OneiqVO mv = oneiqDao.getOneiq(mbsNum);
		
		return mv;
	}
	@Override
	public List<OneiqVO> findAll(){
		
		List<OneiqVO> oneiqList = oneiqDao.findall();
		
		return oneiqList;
	}

	// 답글 부분 
	@Override
	public int replayModifyOneiq(OneiqVO mv) {
		int cnt = oneiqDao.replyOneiq(mv);
		return cnt;
	}
	
	public int replayDeleteOneiq(OneiqVO mv) {
		int cnt = oneiqDao.replyOneiqDelete(mv);
		return cnt;
	}

	
	
	
	
}
