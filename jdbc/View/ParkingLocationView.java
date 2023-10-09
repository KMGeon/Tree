package View;

import java.util.*;

import DAO.*;
import DBUtil.*;
import Others.*;
import VO.*;

public class ParkingLocationView {
	public static String carNo;

	public static void parkingLocationView() throws Exception {
		Loc: while (true) {
			MemberDAO mDao = MemberDAO.getInstance();
			List<MemberVO> mList = mDao.mypageList();
			CarDAO cDao = CarDAO.getInstance();
			CarVO cvo = cDao.mypageCarNo();
			ParkingDAO prkDao = ParkingDAO.getInstance();
			PSDAO psDao = PSDAO.getInstance();
			List<PSVO> list = psDao.psList();

			Banner.banner();
			try {
				if (mList.get(0).getMemId() != null) {
					System.out.printf("\t\t    %s님 환영합니다\n", mList.get(0).getMemName());
				}
			} catch (Exception e) {
				System.out.println();
			}
			System.out.println("\t   주차위치 및 요금확인");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPsLoc() != null) {
					if (i == 4 || i == 9) {
						System.out.print("[" + Font.FONT_YELLOW +"■■■" + Font.RESET+ "]\n");
						System.out.println();
						continue;
					} else {
						System.out.print("[" + Font.FONT_YELLOW +"■■■" + Font.RESET+ "]\t");
						continue;
					}
				}
				if (i == 4 || i == 9) {
					System.out.printf("[%s]\n", list.get(i).getPsNo());
					System.out.println();
				} else {
					System.out.printf("[%s]\t", list.get(i).getPsNo());
				}
			}

			try { // 회원
				List<ParkingVO> optList = prkDao.optList(cvo.getCarNo());
				if (mList.get(0).getMemId() != null) {
					try {
						if(optList.get(0).getCarNo() != null) {
							System.out.println("차량번호 : " + optList.get(0).getCarNo());
							System.out.println("차량위치 : " + prkDao.searchCarLoc(cvo.getCarNo()).getPsNo());
							System.out.println("입차시간 : " + optList.get(0).getPrkIptTime());
							System.out.println("이용시간 : " + optList.get(0).getTime());
							System.out.println("요금 : " + optList.get(0).getFee() + "원");
							System.out.println("[0] 돌아가기 [1] 출차 및 정산");
							System.out.print("번호를 입력해주세요> ");
							String back = ScanUtil.next();
							switch(back) {
							case "0" :
								MemberView.memberView();
								break Loc;
							case "1" :
								OutputFeeView.optFee();
								break Loc;
							}
						}
					}catch(Exception e) {
						System.out.println("현재 회원님의 차량번호로 주차된 차량이 없습니다.");
						System.out.println("[0] 돌아가기 [1] 주차");
						System.out.print("입력> ");
						String back = ScanUtil.next();
						switch(back) {
						case "0" :
							MemberView.memberView();
							break;
						case "1" :
							InputView.inputView();
							break;
						}
					}
				}
			} catch (Exception e) { //비회원
				System.out.println("[0] 돌아가기");
				System.out.print("차량번호를 입력해주세요> ");
				carNo = ScanUtil.next();
				if (carNo.equals("0")) {
					MainView.mainView();
					break Loc;
				}
				List<ParkingVO> optList = prkDao.optList(carNo);
				System.out.println("차량번호 : " + optList.get(0).getCarNo());
				System.out.println("차량위치 : " + prkDao.searchCarLoc(carNo).getPsNo());
				System.out.println("입차시간 : " + optList.get(0).getPrkIptTime());
				System.out.println("이용시간 : " + optList.get(0).getTime());
				System.out.println("요금 : " + optList.get(0).getFee() + "원");

				System.out.println("[0] 돌아가기 [1] 다시입력 [2] 출차 및 정산");
				System.out.print("번호를 입력해주세요> ");
				String back = ScanUtil.next();
				if (back.equals("0")) {
					MainView.mainView();
					break Loc;
				}
				if (back.equals("1")) {
					parkingLocationView();
					break Loc;
				}
				if (back.equals("2")) {
					OutputFeeView.optFee();
					break Loc;
				}
			}
		}
	}
}
