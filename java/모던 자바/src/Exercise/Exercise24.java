package chapter02;

import java.util.Scanner;

public class Exercise24 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("원기둥 밑변의 반지름을 입력하시오.(단위: cm): ");
		double base = scanner.nextDouble();
		
		System.out.print("원기둥의 높이를 입력하시오.(단위: cm): " );
		double height = scanner.nextDouble();
		
		
		
		System.out.print("원기둥 밑변의 넓이는 " + Math.PI * Math.pow(base,2)+ "㎠이고, 원기둥의 부피는 " + ((Math.PI * Math.pow(base,2)) * height) + "㎠이다.");
		
		
		
	}

}
