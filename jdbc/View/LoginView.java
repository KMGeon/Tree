package View;

import DAO.CarDAO;
import DAO.MemberDAO;
import DBUtil.ScanUtil;
import Others.*;
import VO.CarVO;
import VO.MemberVO;

public class LoginView {
	public static String membershipId;
	public static String memId;

	public static void loginView() throws Exception {
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = new MemberVO();
		CarDAO cDao = CarDAO.getInstance();
		Login: while (true) {
			Banner.banner();

			System.out.println("\t\t[로그인메뉴]");
			System.out.println("\t  [1] 로그인");
			System.out.println("\t  [2] 아이디찾기");
			System.out.println("\t  [3] 비밀번호찾기");
			System.out.println("\t  [4] 회원가입");
			System.out.println("\t  [0] 메인메뉴로 돌아가기");
			System.out.print("번호를 입력해 주세요> ");
			String loginMenu = ScanUtil.next();
			switch (loginMenu) {
			case "1": // 로그인
				Loop: while (true) {
					System.out.println("[0] 로그인메뉴로 돌아가기");
					System.out.print("아이디 : ");
					String id = ScanUtil.next();
					LoginView.memId = id;
					if (id.equals("0")) {
						loginView();
						break Loop;
					}
					System.out.print("비밀번호 : ");
					String pw = ScanUtil.next();
					if (pw.equals("0")) {
						loginView();
						break Loop;
					}
					MemberVO vo = mDao.login(id, pw);
					try {
						if (vo.getMemId().equals(id) && vo.getMemPw().equals(pw)) {
							System.out.println(Font.FONT_GREEN + "로그인되었습니다." + Font.RESET);
							MemberView.memberView();
						}
					} catch (NullPointerException e) {
						System.out.println(Font.FONT_RED + "아이디나 비밀번호가 올바르지 않습니다." + Font.RESET);
						continue;
					}
					break Loop;
				}
				break Login;
			case "2": // 아이디 찾기
				Loop: while (true) {
					System.out.println("[0] 돌아가기");
					System.out.print("본인 명의 전화번호를 입력해 주세요 : ");
					String tel = ScanUtil.next();
					if (tel.equals("0")) {
						loginView();
						break Loop;
					}
					MemberVO vo = mDao.findId(tel);
					try {
						if (tel.equals(vo.getMemTel())) {
							System.out.println("회원님의 ID는 " + vo.getMemId() + " 입니다.");
							System.out.println("[0] 돌아가기");
							System.out.print("입력> ");
							String back = ScanUtil.next();
							if (back.equals("0")) {
								loginView();
								break Loop;
							}
						}
					} catch (NullPointerException e) {
						System.out.println(Font.FONT_RED + "전화번호가 올바르지 않습니다." + Font.RESET);
						continue;
					}
					break Login;
				}
			case "3": // 비밀번호 찾기
				Loop: while (true) {
					System.out.println("[0] 돌아가기");
					System.out.print("아이디를 입력해 주세요 : ");
					String id = ScanUtil.next();
					if (id.equals("0")) {
						loginView();
						break Loop;
					}
					System.out.print("본인 명의 전화번호를 입력해 주세요 : ");
					String tel = ScanUtil.next();
					MemberVO vo = mDao.findPw(id, tel);
					try {
						if (id.equals(vo.getMemId()) && tel.equals(vo.getMemTel())) {
							System.out.println("회원님의 비밀번호는 " + vo.getMemPw() + " 입니다.");
							System.out.println("[0] 돌아가기");
							System.out.print("입력> ");
							String back = ScanUtil.next();
							if (back.equals("0")) {
								loginView();
								break Loop;
							}
						}
					} catch (NullPointerException e) {
						System.out.println(Font.FONT_RED + "아이디나 전화번호가 올바르지 않습니다." + Font.RESET);
						continue;
					}
					break Login;
				}

			case "4": // 회원가입
				while (true) {
					System.out.println("[0] 돌아가기");
					System.out.println("아이디 길이: 15자 미만");
					System.out.print("아이디 : ");
					String id = ScanUtil.next();
					LoginView.membershipId = id;
					if (id.equals("0")) {
						loginView();
						break Login;
					}
					MemberVO vo = mDao.DuplicateId(id);
					try {
						if (id.length() >= 15) {
							System.out.println(Font.FONT_RED + "아이디 길이가 15자 이상입니다." + Font.RESET);
							continue;
						}
						if (id.equals(vo.getMemId())) {
							System.out.println(Font.FONT_RED + "중복된 아이디입니다." + Font.RESET);
							continue;
						}
					} catch (NullPointerException e) {// 회원가입 성공
						mDao.membershipId(new MemberVO(id));
					}
					break;

				}
				while (true) {

					System.out.println("비밀번호 길이: 15자 미만");
					System.out.print("비밀번호 : ");
					String pw = ScanUtil.next();
					mVo.setMemPw(pw);
					if (pw.length() >= 15) {
						System.out.println(Font.FONT_RED + "비밀번호 길이가 15자 이상입니다." + Font.RESET);
						continue;
					}
					break;
				}
				while (true) {
					System.out.print("비밀번호 확인 : ");
					String chkPw = ScanUtil.next();
					String pw = mVo.getMemPw();

					if (!chkPw.equals(mVo.getMemPw())) {
						System.out.println("비밀번호가 다릅니다.");
						continue;
					} else {
						mDao.membershipPw(pw);
					}
					break;
				}
				while (true) {
					System.out.println("이름 길이: 20자 미만");
					System.out.print("이름 : ");
					String name = ScanUtil.next();
					if (name.length() >= 20) {
						System.out.println("이름 길이가 20자 이상입니다.");
						continue;
					} else {
						mDao.membershipName(name);
					}
					break;
				}
				while (true) {
					System.out.println("전화번호는 타이푼(-)을 제외하고 입력해주세요.");
					System.out.println("전화번호 길이 : 11자 이하");
					System.out.print("전화번호 : ");
					String tel = ScanUtil.next();
					MemberVO vo = new MemberVO();
					vo = mDao.DuplicateTel(tel);
					try {
						if (tel.length() > 11) {
							System.out.println("전화번호 길이가 11자 이상입니다.");
							continue;
						}
						if (tel.equals(vo.getMemTel())) {
							System.out.println("중복된 전화번호입니다.");
							continue;
						}
					} catch (NullPointerException e) {// 회원가입 성공
						mDao.membershipTel(tel);
					}
					break;
				}
				while (true) {
					System.out.println("차량번호 길이 : 9자 미만");
					System.out.print("차량번호 : ");
					String carNo = ScanUtil.next();
					CarVO vo = cDao.DuplicateCarNo(carNo);
					if (carNo.length() > 9) {
						System.out.println("차량번호 길이가 9자 이상입니다.");
						continue;
					}
					try {
						if (carNo.equals(vo.getCarNo())) {
							System.out.println(Font.FONT_RED + "중복된 차량번호입니다." + Font.RESET);
							continue;
						}
					} catch (Exception e) {
						cDao.membershipCarNo(carNo);
						mDao.membershipFinal();
						System.out.println(Font.FONT_GREEN + "회원가입을 축하드립니다!" + Font.RESET);
						loginView();
					}

					break;
				}
				break Login;
			case "0": // 메인메뉴
				MainView.mainView();
				break Login;
			}
		}
	}

}
