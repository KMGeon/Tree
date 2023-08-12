package JDBCBoard;

import java.util.List;
import java.util.Scanner;

import JDBCBoardService.BoardServiceImpl;
import JDBCBoardService.IBoardService;
import JDBCBoardVO.BoardVO;

public class BoardMain {
	
	private Scanner sc = new Scanner(System.in);
	private IBoardService boardService;
	
	public BoardMain() {
		boardService = BoardServiceImpl.getInstance();
	}
	
	
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시판 전체 목록");
		System.out.println("  2. 새 글 작성");
		System.out.println("  3. 작성 글 수정");
		System.out.println("  4. 작성 글 삭제");
		System.out.println("  5. 게시판 글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("-----------------------");
		System.out.print("원하는 작업 선택 >> ");
		
	}
	
	public void start() {
		int menuNum;
		
		do {
			displayMenu();
			
			menuNum = sc.nextInt();
			
			switch(menuNum) {
				case 1 :
					displayBoardAll();
					break;
				case 2 : 
					insertWrite();
					break;
				case 3 : 
					updateWrite();
					break;
				case 4 : 
					deleteWrite();
					break;
				case 5 : 
					searchWrite();
					break;
				case 6 : 
					System.out.println("작업을 마칩니다.");
					break;
				default : 
					System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}while(menuNum!=6);
		
		
	}


	public void searchWrite() {
		System.out.print("검색할 게시판 글 번호를 입력하세요 : ");
		int boardNo = sc.nextInt();
		System.out.print("검색할 게시판 글 제목을 입력하세요 : ");
		sc.nextLine();
		String boardTitle = sc.nextLine();
		System.out.print("검색할 게시판 글 작성자를 입력하세요 : ");
		String boardWriter = sc.nextLine();
		System.out.print("검색할 게시판 글 내용을 입력하세요 : ");
		String boardContent = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		List<BoardVO> boardList = boardService.searchWrite(bv);
		System.out.println();
		System.out.println("글 번호 \t 글 제목 \t\t 작성자 \t\t 작성일자 \t\t 내용");
		System.out.println("-------------------------------------------------------------------------------");
		
		if(boardList.size() == 0) {
			System.out.println("출력할 게시판 글이 없습니다.");
		}else {
			for(BoardVO bv2 : boardList) {
				System.out.println(bv2.getBoardNo() + "\t" + bv2.getBoardTitle() + "\t\t" + bv2.getBoardWriter() + "\t\t" + bv2.getBoardDate() + "\t" + bv2.getBoardContent());
			}
		}
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("출력 끝.");
	
	
	}


	public void deleteWrite() {
		System.out.print("삭제할 글 번호를 입력하시오 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		BoardVO bv = new BoardVO();
		
		bv.setBoardNo(boardNo);
		
		int cnt = boardService.deleteWrite(boardNo);
		
		if(cnt > 0) {
			System.out.println("게시판 글 삭제 성공!");
		}else {
			System.out.println("게시판 글 삭제 실패!!!");
		}
		
	}


	public void updateWrite() {
		
		System.out.print("수정할 글 번호를 입력하세요 : ");
		int boardNo = sc.nextInt();
		
		sc.nextLine();
		System.out.print("수정할 글 제목을 입력하세요 : ");
		String boardTitle = sc.nextLine(); 
		System.out.print("수정할 작성자를 입력하세요 : ");
		String boardWriter = sc.nextLine();
		System.out.print("수정할 내용을 입력하세요 : ");
		String boardContent = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		
		bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		int cnt = boardService.updateWrite(bv);
		
		if(cnt > 0) {
			System.out.println("게시판 글 수정 성공!");
		}else {
			System.out.println("게시판 글 수정 실패!!!");
		}
		
	}


	public void insertWrite() {
		
		System.out.println("새 글 작성");
		System.out.print("글제목 입력 : " );
		sc.nextLine();
		String boardTitle = sc.nextLine();
		System.out.print("작성자 입력 : ");
		String  boardWriter = sc.nextLine();
		System.out.print("글 내용 입력 : ");
		String boardContent = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		int cnt = boardService.insertWrite(bv);
		
		if(cnt > 0) {
			System.out.println("게시글 등록 성공!");
		}else {
			System.out.println("게시글 등록 실패!!!");
		}
	}


	public void displayBoardAll() {
		
		List<BoardVO> boardList = boardService.displayBoardAll();
		System.out.println();
		System.out.println("글 번호 \t 글 제목 \t\t 작성자 \t\t 작성일자 \t\t 내용");
		System.out.println("-------------------------------------------------------------------------------");
		
		
		if(boardList.size() == 0) {
			System.out.println("출력할 게시판 글이 없습니다.");
		}else {
			for(BoardVO bv : boardList) {
				System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t\t" + bv.getBoardWriter() + "\t\t" + bv.getBoardDate() + "\t" + bv.getBoardContent());
			}
		}
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("출력 끝.");
	}
	
	public static void main(String[] args) {
		BoardMain bm = new BoardMain();
		bm.start();
	}
	
}
