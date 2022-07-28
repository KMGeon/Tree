package View;

import DBUtil.*;
import Others.*;

public class MainView {
	public static String memGd = "비회원";
	public static void mainView() throws Exception {
		Main : while(true) {
			String mainMenu = null;
			Banner.banner();
			
			System.out.println("\t      [메인메뉴]");
			System.out.println("\t  [1] 로그인");
			System.out.println("\t  [2] 주차");
			System.out.println("\t  [3] 주차위치 및 요금 확인");
			System.out.println("\t  [4] 출차 및 정산");
			System.out.println("\t  [5] 납부내역 확인");
			System.out.println("\t  [0] 종료");
			System.out.print("번호를 입력해 주세요> ");
			mainMenu = ScanUtil.next();
			switch(mainMenu) {
				case "0": // 종료
					System.out.println("이용해 주셔서 감사합니다.");
					System.out.println("프로그램 종료.");
					break Main;
					
				case "1": //로그인
					LoginView.loginView();
					break Main;
					
				case "2": //주차
					InputView.inputView();
					break Main;
					
				case "3": //주차위치 및 요금 확인
					ParkingLocationView.parkingLocationView();
					break Main;
					
				case "4": //출차 및 정산
					OutputFeeView.optFee();
					break Main;
					
				case "5": //납부내역 확인
					PaymentView.paymentView();
					break Main;
					
				case "99": //관리자
					AdminView.administratorView();
					break Main;
			}
			
		}
	}
}
