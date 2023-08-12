package kr.or.funding.ptImfor;

import java.util.List;

public class PtImforServiceImpl implements IPtImforService{
	
	private static IPtImforService ptimfprService;
	
	private IPtImforDAO ptimforDao;
	
	private PtImforServiceImpl() {
		ptimforDao = PtImforDAOImpl.getInstance();
	}
	
	public static IPtImforService getInstance() {
		if(ptimfprService == null) {
			ptimfprService = new PtImforServiceImpl();
		}
		return ptimfprService;
	}

	@Override
	public int reaisterPtImfor(PtImforVO pv) {
		int cnt = ptimforDao.insertPtRegister(pv);
		System.out.println("추가");
		return cnt;
	}

	@Override
	public List<PtImforVO> findAll() {
		
		List<PtImforVO> ptimforList = ptimforDao.findall();
		
		return ptimforList;
	}

	@Override
	public List<PtImforVO> atchAll() {
		
		List<PtImforVO> atchList = ptimforDao.acthAll();
		
		return atchList;
	}

	@Override
	public List<PtImforVO> selectDetail(PtImforVO pv) {
		List<PtImforVO> selectDetail = ptimforDao.selectDetail(pv);
		return selectDetail;
	}

	
	
}
