package kr.or.funding.milgDet;

public class MilgDetService {
	private static MilgDetService mileDetService;

	private MilgDetDAO milgDetDAO;

	private MilgDetService() {
		milgDetDAO = MilgDetDAO.getInstance();
	}

	public static MilgDetService getInstance() {

		if (mileDetService == null) {
			mileDetService = new MilgDetService();
		}
		return mileDetService;
	}
	
	public MilgDetVO insertMilg(MilgDetVO mv) {
		return null;
	}
}
