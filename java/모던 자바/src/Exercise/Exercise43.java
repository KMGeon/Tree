package chapter5;

import java.util.Scanner;

public class Exercise43 {

	public static void main(String[] args) {
		System.out.print("점수를 입력하세요.\n프로그래밍 기초: ");
		Scanner scanner = new Scanner(System.in);
		int Basic = scanner.nextInt();
		System.out.print("데이터베이스: ");
		int DB = scanner.nextInt();
		System.out.print("화면구현: ");
		int Interface = scanner.nextInt();
		System.out.print("애플리케이션 구현: ");
		int App = scanner.nextInt();
		System.out.print("머신러닝: ");
		int Ai = scanner.nextInt();
		
		int total = Basic + DB + Interface + App +Ai;
		double average = (double) total/5;
		System.out.println(total);
		System.out.println(average);
		
		if(average>=90) {
			
			System.out.println("학점: A");
			
		}else if(average>=80 && average<90 ) {
			
			System.out.println("학점: B");
			
		}else if(average>=70 && average<80 ) {
			
			System.out.println("학점: C");
			
		}else if(average>=60 && average<70 ) {
			
			System.out.println("학점: D");
			
		}else {
			
			System.out.println("학점: F");
			
		}

		scanner.close();	
	}

}
