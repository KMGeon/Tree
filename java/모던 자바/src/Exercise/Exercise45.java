package chapter5;

import java.util.Random;
import java.util.Scanner;

public class Exercise45 {

	public static void main(String[] args) {
		
		Random random = new Random();
		int randomNum = random.nextInt(100)+1;
		
		Scanner scanner = new Scanner(System.in);
		int one =0;
		while(one != randomNum) {
			
			System.out.print("1부터 100 사이의 정수 중 하나를 선택하세요: ");
			one = scanner.nextInt();
			if(randomNum>one) {
			
				System.out.println("정답은 더 큰 수입니다.");
				
						
			}else if (randomNum<one){
				
				System.out.println("정답은 더 작은 수입니다.");
				
				
			}
			
		}
		
		System.out.println("정답입니다.");
		System.out.println("게임을 종료합니다.");
		scanner.close();
	}

}
