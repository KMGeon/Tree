package View;

import java.util.*;

import DAO.*;
import DBUtil.*;
import Others.*;
import VO.*;

public class AdminView {
	static String name;

	public static void administratorView() throws Exception {
		PaymentDAO pDao = PaymentDAO.getInstance();
		MemberDAO mDao = MemberDAO.getInstance();
		PSDAO psDao = PSDAO.getInstance();
		List<PSVO> list = psDao.psList();
		String locationMenu = null;
		String ynMenu = null;
		Con: while (true) {
			System.out.println("관리자로 접속하시겠습니까?");
			System.out.println("[1] 예 [2] 아니오");
			System.out.print("입력> ");
			ynMenu = ScanUtil.next();
			if (!(ynMenu.equals("1") || ynMenu.equals("2"))) {
				System.out.println("올바르지 않은 입력입니다.");
//				administratorView();
				administratorView();
				break Con;
			}
			switch (ynMenu) {
			case "1":
				Login: while (true) {
					System.out.println("[0] 돌아가기");
					System.out.print("아이디 : ");
					String id = ScanUtil.next();
					if (id.equals("0")) {
						MainView.mainView();
						break;
					}
					System.out.print("비밀번호 : ");
					String pw = ScanUtil.next();
					if (id.equals("admin") && pw.equals("99")) {
						break Login;
					} else {
						System.out.println("아이디나 비밀번호가 다릅니다.");
						continue Login;
					}
				}
				Name: while (true) {
					System.out.print("관리자님 성함을 입력해주세요> ");
					name = ScanUtil.next();
					System.out.println("관리자님의 성함이 " + name + "이(가) 맞습니까?");
					System.out.println("[1] 예 [2] 아니오");
					System.out.print("번호를 입력해주세요> ");
					ynMenu = ScanUtil.next();
					if (!(ynMenu.equals("1") || ynMenu.equals("2"))) {
						System.out.println("입력이 올바르지 않습니다.");
						continue Name;
					}
					switch (ynMenu) {
					case "1":
						System.out.println(name + "관리자님 환영합니다.");
						break Name;
					case "2":
						continue Name;
					}
				}
				break;
			case "2":
				MainView.mainView();
				break;
			}
			break Con;
		}

		Admin: while (true) {
			System.out.println("\t\t\t\t   관리자 : " + name);
			System.out.println("[0] 로그아웃\t[1] 납부 단일 검색\t[2] 납부 전체 출력");
			System.out.println("[3] 주차 현황\t[4] 회원 단일 검색\t[5] 회원 전체 출력");
			System.out.print("번호를 입력해 주세요> ");
			String adminMenu = ScanUtil.next();

			switch (adminMenu) {
			case "0": // mainView돌아가기
				MainView.mainView();
				break Admin;
			case "1": // 납부내역 단일 검색
				Banner.banner();
				Loop: while (true) {
					String searchCar = null;
					System.out.println("[0] 돌아가기");
					System.out.print("검색할 차량번호를 입력하시오> ");
					searchCar = ScanUtil.next();
					if (searchCar.equals("0")) {
						continue Admin;
					}
					List<PaymentVO> data = pDao.drawSeachData(searchCar);
					PayListBanner.banner();
					for (int i = 0; i < data.size(); i++) {
						System.out.println(data.get(i));
					}
					continue Loop;
				}
			case "2": // 납부내역 전체 출력
				Banner.banner();
				PayListBanner.banner();
				List<PaymentVO> partialOutputm = pDao.partialOutput(1, 5);
				for (int i = 0; i < partialOutputm.size(); i++) {
					System.out.println(partialOutputm.get(i) + "\t");
				}
				System.out.println("[0] 돌아가기 \t\t\t  [" + Font.FONT_YELLOW + "1" + Font.RESET + "][2][3][4]");
				Loop: while (true) {
					System.out.print("번호를 입력하세요> ");
					String page = ScanUtil.next();
					if (page.equals("0")) {
						continue Admin;
					}
					if (page.equals("1")) {
						PayListBanner.banner();
						List<PaymentVO> partialOutput = pDao.partialOutput(1, 5);
						for (int i = 0; i < partialOutput.size(); i++) {
							System.out.println(partialOutput.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [" + Font.FONT_YELLOW + "1" + Font.RESET + "][2][3][4]");
						continue Loop;
					}
					if (page.equals("2")) {
						PayListBanner.banner();
						List<PaymentVO> partialOutput = pDao.partialOutput(6, 10);
						for (int i = 0; i < partialOutput.size(); i++) {
							System.out.println(partialOutput.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [1][" + Font.FONT_YELLOW + "2" + Font.RESET + "][3][4]");
						continue Loop;
					}
					if (page.equals("3")) {
						PayListBanner.banner();
						List<PaymentVO> partialOutput = pDao.partialOutput(11, 15);
						for (int i = 0; i < partialOutput.size(); i++) {
							System.out.println(partialOutput.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [1][2][" + Font.FONT_YELLOW + "3" + Font.RESET + "][4]");
						continue Loop;
					}
					if (page.equals("4")) {
						PayListBanner.banner();
						List<PaymentVO> partialOutput = pDao.partialOutput(16, 20);
						for (int i = 0; i < partialOutput.size(); i++) {
							System.out.println(partialOutput.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [1][2][3][" + Font.FONT_YELLOW + "4" + Font.RESET + "]");
						continue Loop;
					}
					break Loop;
				}
			case "3": // 주차 현황
				Banner.banner();
				Loop: while (true) {
					System.out.println("\t       주차 현황");
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getPsLoc() != null || list.get(i).getPsNo().equals(locationMenu)) {
							if (i == 4 || i == 9) {
								System.out.print("[" + Font.FONT_BLUE + "■■■" + Font.RESET + "]\n");
								System.out.println();
								continue;
							} else {
								System.out.print("[" + Font.FONT_BLUE + "■■■" + Font.RESET + "]\t");
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
					try {
						System.out.println("[0] 돌아가기 ");
						System.out.print("번호를 입력해 주세요> ");
						String psNo = ScanUtil.next();
						if (psNo.equals("0")) {
							continue Admin;
						}
						ParkingDAO prkDao = ParkingDAO.getInstance();
						List<ParkingVO> optList = prkDao.psaList(psNo);
						System.out.println(optList.get(0).getCarNo());
						System.out.println("차량번호 : " + optList.get(0).getCarNo());
						System.out.println("입차시간 : " + optList.get(0).getPrkIptTime());
						System.out.println("이용시간 : " + optList.get(0).getTime());
						System.out.println("요금 : " + optList.get(0).getFee() + "원");
						continue Loop;
					} catch (Exception e) {
						System.out.println("해당 자리에는 주차된 차량이 없습니다.");
						continue Loop;
					}
				}
			case "4": // 회원 단일 검색
				Banner.banner();
				Loop: while (true) {
					String searchId = null;
					System.out.println("[0] 돌아가기");
					System.out.print("회원 ID> ");
					searchId = ScanUtil.next();
					if (searchId.equals("0")) {
						continue Admin;
					}
					List<MemberVO> adminSearchList = mDao.adminSearchList(searchId);
					CarDAO cDao = CarDAO.getInstance();
					List<CarVO> adminSearchList2 = cDao.adminSearchList(searchId);
					MemListBanner.banner();
					for (int i = 0; i < adminSearchList.size(); i++) {
						System.out.print("     "+adminSearchList.get(i).getMemId()+"     ");
						System.out.print(adminSearchList.get(i).getMemPw()+"         ");
						System.out.print(adminSearchList.get(i).getMemName()+"         ");
						System.out.print(adminSearchList.get(i).getMemTel()+"         ");
						System.out.println(adminSearchList2.get(i).getCarNo());
					}
					continue Loop;
				}
			case "5": // 회원 전체 출력
				Banner.banner();
				MemListBanner.banner();
				List<MemberVO> partialOutputl = mDao.partialOutput(1, 5);
				for (int i = 0; i < partialOutputl.size(); i++) {
					System.out.println(partialOutputl.get(i));
				}
				System.out.println("[0] 돌아가기 \t\t\t  [" + Font.FONT_YELLOW + "1" + Font.RESET + "][2][3]");
				Loop: while (true) {
					System.out.print("번호를 입력하세요> ");
					String page = ScanUtil.next();
					if (page.equals("0")) {
						continue Admin;
					}
					if (page.equals("1")) {
						MemListBanner.banner();
						List<MemberVO> partialOutputll = mDao.partialOutput(1, 5);
						for (int i = 0; i < partialOutputll.size(); i++) {
							System.out.println(partialOutputll.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [" + Font.FONT_YELLOW + "1" + Font.RESET + "][2][3]");
						continue Loop;
					}
					if (page.equals("2")) {
						MemListBanner.banner();
						List<MemberVO> partialOutputll = mDao.partialOutput(6, 10);
						for (int i = 0; i < partialOutputll.size(); i++) {
							System.out.println(partialOutputll.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [1][" + Font.FONT_YELLOW + "2" + Font.RESET + "][3]");
						continue Loop;
					}
					if (page.equals("3")) {
						MemListBanner.banner();
						List<MemberVO> partialOutputll = mDao.partialOutput(11, 15);
						for (int i = 0; i < partialOutputll.size(); i++) {
							System.out.println(partialOutputll.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [1][2][" + Font.FONT_YELLOW + "3" + Font.RESET + "]");
						continue Loop;
					}
					if (page.equals("4")) {
						MemListBanner.banner();
						List<MemberVO> partialOutputll = mDao.partialOutput(16, 20);
						for (int i = 0; i < partialOutputll.size(); i++) {
							System.out.println(partialOutputll.get(i) + "\t");
						}
						System.out.println("[0] 돌아가기 \t\t\t  [1][2][" + Font.FONT_YELLOW + "3" + Font.RESET + "]");
						continue Loop;
					}
					break Loop;
				}
			}
			break Admin;
		}
	}
}