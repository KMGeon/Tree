package chapter4;

import java.security.SecureRandom;

public class SwitchNobreakCaseExample {

	public static void main(String[] args) {
		
		SecureRandom secureRandom = new SecureRandom();
		int time = secureRandom.nextInt(4)+8;
		
		switch(time) {
		
			case 8: 
				System.out.println("출근합니다.");
			
			case 9: 
				System.out.println("회의를 합니다.");
				
			case 10: 
				System.out.println("업무를 봅니다.");
				
			default: 
				System.out.println("외근을 나갑니다.");
			
		
		}
		

	}

}
