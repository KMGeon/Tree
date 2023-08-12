package chapter5;

import java.util.Random;

public class Exercise49 {

	public static void main(String[] args) {
		int[] dice = new int[6];

		Random random = new Random();
		System.out.println("-------------");
		System.out.println("면        빈도");
		System.out.println("-------------");
		
		for(int i=0; i<10000; i++) {
						
			dice[random.nextInt(6)]++;
			
			}
			
		/*	if(randomNum==1) {
				dice[0]++;
			}else if(randomNum==2) {
				dice[1]++;
			}else if(randomNum==3) {
				dice[2]++;
			}else if(randomNum==4) {
				dice[3]++;
			}else if(randomNum==5) {
				dice[4]++;
			}else if(randomNum==6) {
				dice[5]++;
			}	*/
			
		
		for(int j=0; j<6; j++) {
			System.out.println(j+1+"        "+dice[j]);
		}
	}

}
