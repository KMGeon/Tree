package View;

import java.util.*;

import DAO.*;
import DBUtil.*;
import Others.*;
import VO.*;

public class MemberView {
	public static String memId;
	
	public static void memberView() throws Exception {
		String loginMenu = null;
		Member : while (true) {
			MemberDAO mDao = MemberDAO.getInstance();
			List<MemberVO> mList = mDao.mypageList();
			Banner.banner();
			
			System.out.printf("\t\t    %s님 환영합니다\n", mList.get(0).getMemName()); 
			System.out.println("\t\t[회원메뉴]");
			System.out.println("\t  [1] 마이페이지");
			System.out.println("\t  [2] 주차");
			System.out.println("\t  [3] 주차위치 및 요금 확인");
			System.out.println("\t  [4] 출차 및 정산");
			System.out.println("\t  [5] 납부내역 확인");
			System.out.println("\t  [0] 로그아웃");
			System.out.print("번호를 입력해 주세요> ");
			loginMenu = ScanUtil.next();

			switch (loginMenu) {
			case "0": //로그아웃
				LoginView.memId = null;
				System.out.println(Font.FONT_RED + "로그아웃 되었습니다." + Font.RESET);
				MainView.mainView();
				break Member;
			case "1": // 마이페이지
				MyPageView.myPageView();
				break Member;
			case "2": // 주차
				InputView.inputView();
				break Member;
			case "3": // 주차위치 및 요금 확인
				ParkingLocationView.parkingLocationView();
				break Member;
			case "4": // 출차 및 정산
				OutputFeeView.optFee();
				break Member;
			case "5": // 납부내역 확인
				PaymentView.paymentView();
				break Member;
			}
		}
	}
}
