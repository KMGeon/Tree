package chapter20;

import java.util.List;
import java.util.Scanner;

public class MemoView {
	public void init(MemoController controller) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
program: while(true) {
		System.out.println("------------------------------------");
		System.out.println("1.목록| 2.등록 | 3.수정 | 4.삭제 | 5.종료");
		System.out.println("------------------------------------");
		System.out.println("메뉴를 선택하세요.");
		int memu = Integer.parseInt(scanner.nextLine());
		switch (memu) {
		case 1:
			//목록
			List<MemoVO> memos = controller.getMemos();
				System.out.println("------------------------------------");
				System.out.println("ID \t 제목 \t 내용 \t 등록일자 \t 수정일자");
				System.out.println("------------------------------------");
				for(MemoVO vo : memos) {
				System.out.println(vo);
				}
			break;
		case 2:
			//등록
			System.out.print("제목: ");
			String title = scanner.nextLine();
			System.out.print("내용: ");
			String contents = scanner.nextLine();
			int insertMemo = controller.insertMemo(new MemoVO(title, contents)); 
			if(insertMemo == 1) {
				System.out.println("등록 성공!");
			}else {
				System.out.println("등록 실패");
			}
			break;
		case 3:
			//수정
			System.out.println("수정할 메모 번호: ");
			int searchId = Integer.parseInt(scanner.nextLine());
			System.out.print("제목: ");
			String updateTitle = scanner.nextLine();
			System.out.print("내용: ");
			String updateContents = scanner.nextLine();
			int updateMemo = controller.updateMemo(new MemoVO(searchId, updateTitle, updateContents));
			if(updateMemo == 1) {
				System.out.println("등록 성공!");
			}else {
				System.out.println("등록 실패");
			}
			break;
		case 4:
			//삭제
			System.out.println("삭제할 메모 번호: ");
			int deleteId = Integer.parseInt(scanner.nextLine());
			int deleteMemo = controller.deleteMemo(deleteId);
			if(deleteMemo == 1) {
				System.out.println("삭제 성공!");
			}else {
				System.out.println("삭제 실패!");
			}
			
			break;
		case 5:
			System.out.println("프로그램 종료");
			break program;
		}
		
		}
	}
}
