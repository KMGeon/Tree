package chapter5;

import java.util.Random;
import java.util.Scanner;

public class Exercise45UpLevel {

	public static void main(String[] args) {
		
		//변수를 필요한 부분에서만 쓰고 풀어보기(버리는 법, 메소드블록)
		// 하나 더 추가 (몇 번만에 맞췄나를 넣어보기)
		
		Random random = new Random();
		int randomNum = random.nextInt(100)+1;
		
		Scanner scanner = new Scanner(System.in);
		int one=0;
		int cnt=0;
		while(one != randomNum) {
			
			System.out.print("1부터 100 사이의 정수 중 하나를 선택하세요: ");
			one = scanner.nextInt();
			cnt +=1;
			if(randomNum>one) {
			
				System.out.println("정답은 더 큰 수입니다.");
				
			}else if (randomNum < one){
				
				System.out.println("정답은 더 작은 수입니다.");
			
			}
			
		}

		System.out.println("정답입니다.");
		System.out.println(cnt + "번 만에 맞추셨습니다.");
		System.out.println("게임을 종료합니다.");
		scanner.close();
	}

}
