package chapter4;

import java.security.SecureRandom;

public class IfDiceExample {

	public static void main(String[] args) {
		/*
		 * int randomNum = (int) (Math.random()*6 +1);
		 * 
		*/
		SecureRandom secureRandom = new SecureRandom();
		int randomNum = secureRandom.nextInt(6)+1;
		
		if(randomNum==1) {
			
			System.out.println("1번이 나왔습니다.");
			
		}else if(randomNum==2) {
			
			System.out.println("2번이 나왔습니다.");
			
		}else if(randomNum==3) {
			
			System.out.println("3번이 나왔습니다.");
			
		}else if(randomNum==4) {
			
			System.out.println("4번이 나왔습니다.");
			
		}else if(randomNum==5) {
			
			System.out.println("5번이 나왔습니다.");
			
		}else {
			
			System.out.println("6번이 나왔습니다.");
			
		}
		
		/* 랜덤 숫자 만들기
		 * 
		 * 1. 좋지 않은 방식 
		 * int randomNum = (int)(math.random()*총개수) + 시작숫자;
		 * 
		 * 2. 좋은 방식
		 * Random random = new Random();
		 * int randomNum = random.nextInt(총개수) + 시작숫자;
		 * 
		 * 3.보안적용 version
		 * SecureRandom secureRandom = new SecureRandom();
		 * int randomNum = secureRandom.nextInt(총개수) + 시작숫자;
		 *
		 */

		
	}

}
