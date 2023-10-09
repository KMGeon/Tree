package View;

import java.util.*;

import DAO.*;
import DBUtil.*;
import Others.*;
import VO.*;

public class InputView {
	public static void inputView() throws Exception {
		Input: while (true) {
			String locationMenu = null;
			MemberDAO mDao = MemberDAO.getInstance();
			List<MemberVO> mList = mDao.mypageList();
			ParkingVO prkVo = new ParkingVO();
			ParkingDAO prkDao = ParkingDAO.getInstance();
			PSDAO psDao = PSDAO.getInstance();
			CarDAO cDao = CarDAO.getInstance();
			CarVO cvo = cDao.mypageCarNo();
			List<PSVO> list = psDao.psList();
			List<PSVO> x = psDao.psListX();
			Banner.banner();

			try { // 회원
				if (mList.get(0).getMemId() != null) {
					System.out.printf("\t\t    %s님 환영합니다\n", mList.get(0).getMemName());
				}
				for (int i = 0; i < list.size(); i++) {
					if (cvo.getCarNo().equals(x.get(i).getPsLoc())) {
						System.out.println(Font.FONT_RED + "이미 주차하셨습니다." + Font.RESET);
						System.out.println("[0] 돌아가기");
						System.out.print("입력> ");
						String has = ScanUtil.next();
						if(has.equals("0")) {
							MemberView.memberView();
							break Input;
						}
					}
				}
			} catch (Exception e) { // 비회원
				System.out.println();
			}
			System.out.println("\t      [주차하기]");

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPsLoc() != null || list.get(i).getPsNo().equals(locationMenu)) {
					if (i == 4 || i == 9) {
						System.out.print("[" + Font.FONT_YELLOW +"■■■" + Font.RESET + "]\n");
						System.out.println();
						continue;
					} else {
						System.out.print("[" + Font.FONT_YELLOW +"■■■" + Font.RESET + "]\t");
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
			System.out.println("[0] 돌아가기");
			try { // 회원
				if (mList.get(0).getMemId() != null) {
					System.out.println("차량번호 : " + cvo.getCarNo());
					
				}
			} catch (Exception e) { // 비회원
				System.out.print("차량번호를 입력하세요> ");
				String carNo = ScanUtil.next();
				prkVo.setCarNo(carNo);
				if (prkVo.getCarNo().equals("0")) {
					MainView.mainView();
					break;
				}
				cDao.insertCar(new CarVO(prkVo.getCarNo(), null));
			}
			System.out.print("위치를 입력해주세요> ");
			locationMenu = ScanUtil.next();
			try { // 회원
				if (mList.get(0).getMemId() != null) {
					if (locationMenu.equals("0")) {
						MemberView.memberView();
						break Input;
					}
					prkDao.insertPsNoM(new ParkingVO(cvo.getCarNo(), locationMenu));
					psDao.updatePs(cvo.getCarNo(), locationMenu);
				}
			} catch (Exception e) { // 비회원
				if (locationMenu.equals("0")) {
					MainView.mainView();
					break Input;
				}
				prkDao.insertPsNo(new ParkingVO(locationMenu));
				prkDao.updatePrk(new ParkingVO(prkVo.getCarNo(), locationMenu));
				psDao.updatePs(prkVo.getCarNo(), locationMenu);
			}

			switch (locationMenu) {
			case "A01": case "A02": case "A03": case "A04": case "A05":
			case "B01": case "B02": case "B03": case "B04": case "B05":
				System.out.println("주차중입니다.");
				SleepThread.Loading();
				System.out.println("주차되었습니다.");
				try { // 회원
					if (mList.get(0).getMemId() != null) {
						MemberView.memberView();
						break Input;
					}
				} catch (Exception e) { // 비회원
					MainView.mainView();
					break Input;
				}
			}
		}
	}
}
