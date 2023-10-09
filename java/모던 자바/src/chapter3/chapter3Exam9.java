package chapter3;

import java.util.Scanner;

public class chapter3Exam9 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("첫 번째 수: ");
		double num1 = scanner.nextDouble();
		
		System.out.print("두 번째 수: ");
		double num2 = scanner.nextDouble();
		
		System.out.println("-------------");
		
		double num3 = num1/num2;
		String num5 = String.valueOf(num3);
		String num4 = (num2==0||num2==0.0)?"무한대":num5; 
		
		
		
		System.out.println("결과:"+num4);
		scanner.close();
		
	}

}
