package kr.or.funding.pyHis.service;

import kr.or.funding.pyHis.dao.IPyHisDao;
import kr.or.funding.pyHis.dao.PyHisDaoImpl;
import kr.or.funding.pyHis.vo.PyHisVO;

public class PyHisServiceImpl implements IPyHisService{
	
	private static IPyHisService pyHisService;

	private IPyHisDao pyHisDao;

	private PyHisServiceImpl() {
		pyHisDao = PyHisDaoImpl.getInstance();
	}

	public static IPyHisService getInstance() {

		if (pyHisService == null) {
			pyHisService = new PyHisServiceImpl();
		}
		return pyHisService;
	}

	@Override
	public int insertPyHis(PyHisVO pv) {
		int cnt = pyHisDao.insertPyHis(pv);
		return cnt;
	}
	
	
}
