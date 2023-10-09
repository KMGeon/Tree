package lotto;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	private Scanner scanner;
	private int times;
	
	public void lottoMenu() {
		System.out.println("========================================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("========================================");
		System.out.print("메뉴선택 : ");
	}
	
	public Lotto() {
		scanner = new Scanner(System.in);
		
	}
	
	public void lottoStart() {
		
		while(true) {
			System.out.println();
			lottoMenu();
			
			int menuNum = scanner.nextInt();
			
			switch(menuNum) {
			// 금액을 입력 후 금액에 따라 로또 번호생성(o), 생성된 로또는 중복없이 6개 찍고(o), 거스름돈 보여주기(o)
			case 1:
				System.out.println();
				System.out.println("Lotto 구입 시작");
				System.out.print("금액 입력 : ");
				int price = scanner.nextInt();
				System.out.println();
				System.out.println("행운의 로또번호는 아래와 같습니다.");
				times = price/1000;
				if(times != 0) {
					for(int i=1 ; i < times+1 ; i++) {
						System.out.println("로또 번호" + i + " : " + draw());					
					}
				}
				int remainder = price%1000;
				System.out.println();
				System.out.printf("받은 금액은 %d이고, 거스름돈은 %d입니다.", price, remainder);
				System.out.println();
				break;
			case 2:
				System.out.println();
				System.out.println("감사합니다.");
				return;
			}
				
		}
		
	}
	
	public static void main(String[] args) {
		new Lotto().lottoStart();
	}
	
	public Set<Integer> draw() {
		Set<Integer> lottoNum = new HashSet<>();
		while(lottoNum.size() < 6) {
			Random random = new Random();
			int num = random.nextInt(46)+1;
			lottoNum.add(num);
		}
		return lottoNum;
	}
	// for는 i를 이용해서 while은 count라는 변수를 
	// 선언하고 해보기
}
