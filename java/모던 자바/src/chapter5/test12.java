package chapter5;

import java.util.Random;

public class test12 {

	public static void main(String[] args) {
		Random random = new Random();
		int randomNum = random.nextInt(45)+1;
		
		int[] lotto = new int[6] ;
		
		for(int i=0; i<6; i++) {
			
			
			lotto[i] = randomNum;
			
			
			
			for(int j=1; j<6; j++) {
				
				if(lotto[i]==lotto[j]) {
					
					randomNum = random.nextInt(45)+1;
					
					i--;
										
					}
				
				}
			
			
			
		
			System.out.println("오늘의 행운 번호는 " + lotto[i]);
		}

	
	}

}