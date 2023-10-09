package View;

import java.text.*;
import java.util.*;

import DAO.*;
import DBUtil.*;
import Others.*;
import VO.*;

public class OutputFeeView {
	public static void optFee() throws Exception {
		String ynMenu = null;
		Out: while (true) {
			MemberDAO mDao = MemberDAO.getInstance();
			List<MemberVO> mList = mDao.mypageList();
			CarDAO cDao = CarDAO.getInstance();
			CarVO cvo = cDao.mypageCarNo();
			ParkingDAO prkDao = ParkingDAO.getInstance();
			PaymentDAO pay = PaymentDAO.getInstance();
			PSDAO psDao = PSDAO.getInstance();

			Banner.banner();
			try {
				if (mList.get(0).getMemId() != null) {
					System.out.printf("\t\t    %s님 환영합니다\n", mList.get(0).getMemName());
				}
			} catch (Exception e) {
				System.out.println();
			}
			System.out.println("\t      [출차 및 정산]");
			try {
				if (mList.get(0).getMemId() != null) { // 회원
					List<ParkingVO> oList = prkDao.optList(cvo.getCarNo());
					try {
						if(oList.get(0).getCarNo() != null) {
							prkDao.updateOptTime(new CarVO(cvo.getCarNo()));// 출차시간 sysdate 업데이트
							DecimalFormat df = new DecimalFormat("###,###");
							System.out.println("차량번호 : " + cvo.getCarNo());
							System.out.println("입차시간 : " + oList.get(0).getPrkIptTime());
							System.out.println("현재시간 : " + oList.get(0).getPrkOptTime());
							System.out.println("이용 시간 : " + oList.get(0).getTime());
							System.out.println("정산요금 : " + df.format(oList.get(0).getFee()) + "원");
							System.out.println("결제하시겠습니까?");
							System.out.println("[1] 예 [2] 아니오");
							System.out.print("번호를 입력해주세요> ");
							ynMenu = ScanUtil.next();
							if (ynMenu.equals("1")) {
								Card.card();
								pay.insertPay(cvo.getCarNo()); // 출차하면 Prk테이블의 정보 Pay테이블로 삽입
								pay.updateFee(cvo.getCarNo(), oList.get(0).getFee()); // Prk테이블에 Fee가 없기때문에 Fee 업데이트
								System.out.println("결제를 진행하고 있습니다.");
								SleepThread.Loading();
								System.out.println("결제가 완료되었습니다.");
								System.out.println("이용해주셔서 감사합니다.");
								Thread.sleep(1000);
								System.out.println();
								System.out.println("┌───────────────┐");
								System.out.println("│[Web발신]\t│");
								System.out.println("│DDIT카드6*2*승인\t│");
								System.out.println("│" + mList.get(0).getMemName() + "      \t│"); // vo.memid
								System.out.println("│" + df.format(oList.get(0).getFee()) + "원 일시불\t│");
								System.out.println("│" + oList.get(0).getPrkOptTime().substring(6) + "\t│");
								System.out.println("│어벤져스 컴퍼니\t│");
								int balance = 1000000;
								System.out.println("│잔액 " + df.format((balance -= oList.get(0).getFee())) + "원\t│");
								System.out.print("└───────────────┘");
								prkDao.deletePrk(cvo.getCarNo()); // Prk테이블 출차하는 정보 삭제
								psDao.deletePs(cvo.getCarNo()); // PS테이블 출차하는 정보 Null
							} else if (ynMenu.equals("2")) {
								prkDao.updateOptNull(new CarVO(cvo.getCarNo()));
								MemberView.memberView();
								break Out;
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
			} catch (Exception e) { // 비회원
				System.out.println("[0] 돌아가기");
				System.out.print("차량번호를 입력하세요> ");
				String carNo = ScanUtil.next();

				prkDao.updateOptTime(new CarVO(carNo));// 출차시간 sysdate 업데이트
				if (carNo.equals("0")) {
					MainView.mainView();
					break Out;
				}
				List<ParkingVO> oList = prkDao.optList(carNo);
				DecimalFormat df = new DecimalFormat("###,###");
				System.out.println("차량번호 : " + carNo);
				System.out.println("입차시간 : " + oList.get(0).getPrkIptTime());
				System.out.println("현재시간 : " + oList.get(0).getPrkOptTime());
				System.out.println("이용 시간 : " + oList.get(0).getTime());
				System.out.println("정산요금 : " + df.format(oList.get(0).getFee()) + "원");
				System.out.println("결제하시겠습니까?");
				System.out.println("[1] 예 [2] 아니오");
				System.out.print("번호를 입력해주세요> ");
				ynMenu = ScanUtil.next();
				if (ynMenu.equals("1")) {
					Card.card();
					pay.insertPay(carNo); // 출차하면 Prk테이블의 정보 Pay테이블로 삽입
					pay.updateFee(carNo, oList.get(0).getFee()); // Prk테이블에 Fee가 없기때문에 Fee 업데이트
					System.out.println("결제를 진행하고 있습니다.");
					SleepThread.Loading();
					System.out.println("결제가 완료되었습니다.");
					System.out.println("이용해주셔서 감사합니다.");
					Thread.sleep(1000);
					System.out.println();
					System.out.println("┌───────────────┐");
					System.out.println("│[Web발신]\t│");
					System.out.println("│DDIT카드6*2*승인\t│");
					System.out.println("│비회원      \t│"); // vo.memid
					System.out.println("│" + df.format(oList.get(0).getFee()) + "원 일시불\t│");
					System.out.println("│" + oList.get(0).getPrkOptTime().substring(6) + "\t│");
					System.out.println("│어벤져스 컴퍼니\t│");
					int balance = 1000000;
					System.out.println("│잔액 " + df.format((balance -= oList.get(0).getFee())) + "원\t│");
					System.out.print("└───────────────┘");
					prkDao.deletePrk(carNo); // Prk테이블 출차하는 정보 삭제
					cDao.deleteCar(carNo); // Car테이블 출차하는 정보 삭제
					psDao.deletePs(carNo); // PS테이블 출차하는 정보 Null
				} else if (ynMenu.equals("2")) {
					prkDao.updateOptNull(new CarVO(carNo));
					MainView.mainView();
					break Out;
				}
			}
			break Out;
		}
	}
}