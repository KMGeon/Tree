package chapter3;

import java.util.Scanner;

public class Exercise32 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("수도입니까?(수도: 1, 수도아님: 0) ");
		String num1 = scanner.next();
		
		System.out.print("총 인구는?(단위: 만) ");
		String num2 = scanner.next();
		
		System.out.print("연소득이 1억 이상인 인구는?(단위: 만) ");
		String num3 = scanner.next();
		
		String str1 = "메트로폴리스";
		
		String str2 = "메트로폴리스가 아닌 도시";
		
		
		int num4 = Integer.parseInt(num1);  
		int num5 = Integer.parseInt(num2); 
		int num6 = Integer.parseInt(num3); 
		
		String result = ((num4 == 1 & num5 >= 100) || num6>=50)? str1 :str2;
		
		System.out.println("이 도시는 "+ result +"입니다");
		
		scanner.close();
	}

}
