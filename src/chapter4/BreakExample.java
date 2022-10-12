package chapter4;

import java.security.SecureRandom;

public class BreakExample {

	public static void main(String[] args) {
		while(true) {
			SecureRandom secureRandom = new SecureRandom();
			int num = secureRandom.nextInt(6)+1;
			System.out.println(num);
			if (num==6) {
				break;
			}
			
		}
		System.out.println("프로그램 종료");
	}

}
