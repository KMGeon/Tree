package View;

import java.util.*;

import DAO.*;
import DBUtil.*;
import Others.*;
import VO.*;

public class PaymentView {
	public static void paymentView() throws Exception {
		while (true) {
			MemberDAO mDao = MemberDAO.getInstance();
			List<MemberVO> mList = mDao.mypageList();
			CarDAO cDao = CarDAO.getInstance();
			CarVO cvo = cDao.mypageCarNo();
			PaymentDAO pay = PaymentDAO.getInstance();
			Banner.banner();
			try {
				if (mList.get(0).getMemId() != null) {
					System.out.printf("\t\t    %s님 환영합니다\n", mList.get(0).getMemName());
				}
			}catch(Exception e) {
				System.out.println();
			}
			System.out.println("\t   [납부내역 확인]");
			// 0 누르면 mainView
			
			try { // 회원
				if (mList.get(0).getMemId() != null) {
					PayListBanner.banner();
					List<PaymentVO> drawSeachData = pay.drawSeachData(cvo.getCarNo());
					for (int i = 0; i < drawSeachData.size(); i++) {
						System.out.println(drawSeachData.get(i));
					}
					System.out.println("[0] 돌아가기 [1] 다시입력");
					System.out.print("번호를 입력해주세요> ");
					String back = ScanUtil.next();
					if(back.equals("0")) {
						MemberView.memberView();
						break;
					}
					if(back.equals("1")) {
						paymentView();
					}
				}
			}catch(Exception e) { // 비회원
				System.out.println("[0] 돌아가기");
				System.out.print("차량번호를 입력해주세요> ");
				String inputValue = ScanUtil.next();
				if (inputValue.equals("0")) {
					MainView.mainView();
					break;
				}
				PayListBanner.banner();
				List<PaymentVO> drawSeachData = pay.drawSeachData(inputValue);
				for (int i = 0; i < drawSeachData.size(); i++) {
					System.out.println(drawSeachData.get(i));
				}
				System.out.println("[0] 돌아가기 [1] 다시입력");
				System.out.print("번호를 입력해주세요> ");
				String back = ScanUtil.next();
				if(back.equals("0")) {
					MainView.mainView();
					break;
				}
				if(back.equals("1")) {
					paymentView();
				}
			}
		}
	}
}
