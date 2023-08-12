package chapter5;

import java.util.Scanner;

public class Exercise44 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("가위 바위 보 게임");
		System.out.print("철수: ");
		
		String first = scanner.nextLine();
		
		System.out.print("영희: ");
		
		String two = scanner.nextLine();
		
		String x = "바위";
		String y = "가위";
		String z = "보";
		
		if(first.equals(two)) {
			
			System.out.println("결과: 무승부!");
			
		}else if(first.equals(x)) {
			
			if(two.equals(y)) {
				
				System.out.println("결과: 철수 승리!");
				
			}else if(two.equals(z)) {
				
				System.out.println("결과: 영희 승리!");
				
			}
			
		}else if(first.equals(y)) {
			
			if(two.equals(x)) {
				
				System.out.println("결과: 영희 승리!");
				
			}else if(two.equals(z)) {
				
				System.out.println("결과: 철수 승리!");
				
			}
			
		}else if(first.equals(z)) {
			
			if(two.equals(x)) {
				
				System.out.println("결과: 철수 승리!");
				
			}else if(two.equals(y)) {
				
				System.out.println("결과: 영희 승리!");
				
			}
			
		}
		
		scanner.close();
		
	}

}
