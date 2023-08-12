package chapter3;

import java.util.Scanner;

public class Exercise31 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("첫 번째 숫자를 입력하세요: ");
		int num1 = scanner.nextInt();
		
		System.out.print("두 번째 숫자를 입력하세요: ");
		int num2 = scanner.nextInt();
		
		int bNum = (((num1+num2)+Math.abs(num1-num2))/2);
		int sNum = (((num1+num2)-Math.abs(num1-num2))/2);
		
		int result1 = (bNum / sNum);
		int result2 = (bNum % sNum);
		
		System.out.println("큰 수를 작은 수로 나눈 몫은 "+ result1 +"이고, " + "나머지는 " + result2 + "이다.");
		
		scanner.close();
	}

}
