package chapter5;

import java.util.Random;

public class Exercise47 {

	public static void main(String[] args) {
		/*
		Random random = new Random();
		
		int[] lotto = new int[6] ;
		
		for(int i=0; i<6; i++) {
			int randomNum = random.nextInt(45)+1;
			lotto[i] = randomNum;
			
			for(int j=0; j<i; j++) {
				if(lotto[i]==lotto[j]) {
					i--; 
					break;					
				}
			}
		}
		for(int i=0; i<6; i++) {
		System.out.println("오늘의 행운 번호는 " + lotto[i]);
		}
		*/
		
		Random random = new Random();
	
		int[] lottoNum = new int[45];
		
		for(int k=0; k<lottoNum.length; k++) {
			lottoNum[k] = k+1;	
		}
		
		//int temp = 0;
		for(int i=0; i<lottoNum.length; i++) {
			int randomNum = random.nextInt(45);
			
			int temp=lottoNum[i];
			lottoNum[i]= lottoNum[randomNum];
			lottoNum[randomNum]= temp;
		}
		
		for(int j=0; j<6; j++) {
		System.out.println("오늘의 행운번호는 "+lottoNum[j]);
		}
		
		
	}

}
