package hotel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	private Scanner scanner;
	private Map<Integer,String> map = new HashMap<>();
	
	public Hotel() {
		scanner = new Scanner(System.in);
	}
	
	public void hotelMenu() {
		System.out.println("************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃  3.객실상태  4.업무종료");
		System.out.println("************************************");
		System.out.print("메뉴선택 => ");
	}
	
	public static void main(String[] args) {
		new Hotel().hotelStart();
	}
	
	public void hotelStart() {
		
		while(true) {
			
			hotelMenu();
			
			int menuNum = scanner.nextInt();
			
			switch(menuNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomStatus();
				break;
			case 4:
				System.out.println();
				System.out.println("****************");
				System.out.println("호텔 문을 닫습니다.");
				System.out.println("****************");
				return;
			
			}
		}
	}
	
	
	public void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = scanner.nextInt();
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scanner.next();
		
		
		if(map.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			System.out.println();
		} else {
			map.put(roomNum, name);
			System.out.println("체크인 되었습니다.");
			System.out.println();
		}
	}
	
	public void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = scanner.nextInt();
		
		
		if(map.get(roomNum)==null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.\n");
		}else {			
			map.remove(roomNum);
			System.out.println("체크아웃 되었습니다.\n");
		}
		

		
	}
	
	public void roomStatus() {
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		Iterator<Entry<Integer, String>> entryIt = entrySet.iterator();
		
		while(entryIt.hasNext()) {
			Entry<Integer, String> entry = entryIt.next();
			
			System.out.println("방번호 : " + entry.getKey()+ ", 투숙객 : " + entry.getValue());
		}
		System.out.println();
		
	}
	
	
}


//문제)
//
//호텔 운영을 관리하는 프로그램 작성.(Map이용)
// - 키값은 방번호 
// 
//실행 예시)
//
//**************************
//호텔 문을 열었습니다.
//**************************
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//*******************************************
//메뉴선택 => 1 <-- 입력
//
//어느방에 체크인 하시겠습니까?
//방번호 입력 => 101 <-- 입력
//
//누구를 체크인 하시겠습니까?
//이름 입력 => 홍길동 <-- 입력
//체크인 되었습니다.
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//*******************************************
//메뉴선택 => 1 <-- 입력
//
//어느방에 체크인 하시겠습니까?
//방번호 입력 => 102 <-- 입력
//
//누구를 체크인 하시겠습니까?
//이름 입력 => 성춘향 <-- 입력
//체크인 되었습니다
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//	*******************************************
//메뉴선택 => 3 <-- 입력
//
//방번호 : 102, 투숙객 : 성춘향
//방번호 : 101, 투숙객 : 홍길동
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//*******************************************
//메뉴선택 => 2 <-- 입력
//
//어느방을 체크아웃 하시겠습니까?
//방번호 입력 => 101 <-- 입력
//체크아웃 되었습니다.
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//*******************************************
//메뉴선택 => 1 <-- 입력
//
//어느방에 체크인 하시겠습니까?
//방번호 입력 => 102 <-- 입력
//
//누구를 체크인 하시겠습니까?
//이름 입력 => 허준 <-- 입력
//102방에는 이미 사람이 있습니다.
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//*******************************************
//메뉴선택 => 2 <-- 입력
//
//어느방을 체크아웃 하시겠습니까?
//방번호 입력 => 101 <-- 입력
//101방에는 체크인한 사람이 없습니다.
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//*******************************************
//메뉴선택 => 3 <-- 입력
//
//방번호 : 102, 투숙객 : 성춘향
//
//*******************************************
//어떤 업무를 하시겠습니까?
//1.체크인  2.체크아웃 3.객실상태 4.업무종료
//*******************************************
//메뉴선택 => 4 <-- 입력
//
//**************************
//호텔 문을 닫았습니다.
//**************************
