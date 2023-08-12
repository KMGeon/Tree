package kr.or.funding.acm.sercive;

import kr.or.funding.acm.dao.AcommodationDAOImpl;
import kr.or.funding.acm.dao.IAcommodationDAO;
import kr.or.funding.acm.vo.AcommodationVO;

public class AcommodationServiceImpl implements IAcommodationService {

	private static IAcommodationService acmService;

	private IAcommodationDAO acmDao;

	private AcommodationServiceImpl() {
		acmDao = AcommodationDAOImpl.getInstance();
	}

	public static IAcommodationService getInstance() {
		if (acmService == null) {
			acmService = new AcommodationServiceImpl();
		}

		return acmService;
	}

	@Override
	public int register(AcommodationVO acmv) {
		int cnt = acmDao.insert(acmv);
		return cnt;
	}

}