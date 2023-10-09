package View;

import java.util.List;

import DAO.*;
import DBUtil.ScanUtil;
import Others.Banner;
import VO.CarVO;
import VO.MemberVO;

public class MyPageView {
	public static void myPageView() throws Exception {
		MY: while (true) {
			MemberDAO mDao = MemberDAO.getInstance();
			CarDAO cDao = CarDAO.getInstance();
			List<MemberVO> mList = mDao.mypageList();
			CarVO cvo = cDao.mypageListCarNo();
			Banner.banner();
			System.out.printf("\t\t    %s님 환영합니다\n", mList.get(0).getMemName());
			System.out.println("\t     [마이페이지]");
			System.out.println("아이디: " + mList.get(0).getMemId());
			System.out.println("비밀번호 : " + mList.get(0).getMemPw());
			System.out.println("이름: " + mList.get(0).getMemName());
			System.out.println("전화번호: " + mList.get(0).getMemTel());
			System.out.println("차량번호: " + cvo.getCarNo());
			System.out.println("[1] 회원정보 수정 [2] 회원탈퇴 [0] 돌아가기");
			System.out.print("번호를 입력해주세요> ");
			int myPage = ScanUtil.nextInt();
			switch (myPage) {
			case 0:
				MemberView.memberView();
				break MY;
			case 1:
				Loop: while (true) {
					System.out.println("변경하실 정보를 선택하세요.");
					System.out.println("[1] 비밀번호");
					System.out.println("[2] 이름");
					System.out.println("[3] 전화번호");
					System.out.println("[4] 차량번호");
					System.out.println("[0] 돌아가기");
					System.out.println("이외의 정보변경을 원하실 경우 ☏042-222-8202로 전화주세요");
					System.out.print("변경하실 정보를 입력하세요> ");
					String updateInfo = ScanUtil.next();
					switch (updateInfo) {
					case "0":
						myPageView();
						break Loop;
					case "1":
						System.out.print("변경할 비밀번호를 입력해주세요> ");
						String updatePw = ScanUtil.next();
						String id = mList.get(0).getMemId();
						mDao.mypagePw(updatePw, id);
						System.out.println("비밀번호를 " + updatePw + "(으)로 변경했습니다.");
						myPageView();
						break Loop;
					case "2":
						System.out.print("변경할 이름을 입력해주세요> ");
						String updateName = ScanUtil.next();
						id = mList.get(0).getMemId();
						mDao.mypageName(updateName, id);
						System.out.println("이름을 " + updateName + "(으)로 변경했습니다.");
						myPageView();
						break Loop;

					case "3":
						System.out.print("변경할 전화번호를 입력해주세요(타이푼(-)제외)> ");
						String updateTel = ScanUtil.next();
						id = mList.get(0).getMemId();
						mDao.mypageTel(updateTel, id);
						System.out.println("전화번호를 " + updateTel + "(으)로 변경했습니다.");
						myPageView();
						break Loop;
					case "4":
						System.out.print("변경할 차량번호를 입력해주세요> ");
						String updateCar = ScanUtil.next();
						id = mList.get(0).getMemId();
						ParkingDAO prkDao = ParkingDAO.getInstance();
						try {
							prkDao.searchTable(id);
							if (cvo.getCarNo() != null) {
								prkDao.prkUpNull();
								cDao.mypageCarNo(updateCar, id);
								prkDao.prkUpCarNo(updateCar);
							}
						} catch (Exception e) {
							cDao.mypageCarNo(updateCar, id);
						}
						System.out.println("차량번호를 " + updateCar + "(으)로 변경했습니다.");
						myPageView();
						break Loop;
					}
				}
				break MY;
			case 2:
				System.out.println("회원탈퇴를 하시겠습니까?");
				System.out.print("[1]예 [2]아니오> ");
				String deleteInfo = ScanUtil.next();
				if (deleteInfo.equals("1")) {
					Loop: while (true) {
						System.out.println("회원탈퇴를 하시면 24시간동안 재가입이 불가능합니다.");
						System.out.println("그래도 회원탈퇴를 원하시면 \"회원탈퇴\"를 입력해주세요");
						System.out.println("[0] 돌아가기");
						System.out.print("입력> ");
						String delete = ScanUtil.next();
						if (delete.equals("0")) {
							myPageView();
							break Loop;
						}

						if (delete.equals("회원탈퇴")) {
							System.out.println("회원탈퇴가 정상적으로 되었습니다. 이용해 주셔서 감사합니다.");
							cDao.mypageCarNoDelete(mList.get(0).getMemId());
							mDao.mypageDelete(mList.get(0).getMemId());
							MainView.mainView();
							break Loop;
						}
					}
				}
				if (deleteInfo.equals("2")) {
					myPageView();
					break MY;
				}
				break MY;
			}
		}
	}
}
