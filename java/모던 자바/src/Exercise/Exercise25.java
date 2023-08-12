package chapter02;

import java.util.Scanner;

public class Exercise25 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("500원 짜리 동전의 갯수: " );
		
		int fiveHundred = scanner.nextInt();
		fiveHundred = fiveHundred*500;
		
		System.out.print("100원 짜리 동전의 갯수: " );
		
		int oneHundred = scanner.nextInt();
		oneHundred = oneHundred*100;
		
		System.out.print("50원 짜리 동전의 갯수: " );
		
		int fifty = scanner.nextInt();
		fifty = fifty*50;
		
		System.out.print("10원 짜리 동전의 갯수: " );
		
		int ten = scanner.nextInt();
		ten = ten*10;
		
		int sum = fiveHundred + oneHundred + fifty + ten;
		
		System.out.printf("저금통 안의 동전의 총 액수: %d" ,sum);
		
		

	}

}
