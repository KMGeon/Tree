package chapter5;

import java.util.Scanner;

public class Exercise42 {

	public static void main(String[] args) {
		
		//switch문을 이용한 풀이와 if를 쓴다면 위에서 아래로 
		//진행방향 생각해서 풀어보기
		
		System.out.print("월을 입력하세요(1~12): ");
		
		Scanner scanner = new Scanner(System.in);
		int month = scanner.nextInt();
		
		if(3<=month && month<=5) {
			
			System.out.println(month + "월은 봄입니다.");
			
		}else if(6<=month && month<=8) {
			
			System.out.println(month + "월은 여름입니다.");
			
		}else if(9<=month && month<=11) {
			
			System.out.println(month + "월은 가을입니다.");
			
		}else if(12==month || (0<=month && month<=2)) {
			
			System.out.println(month + "월은 겨울입니다.");
			
		}else {
			
			System.out.println(month + "월은 잘못된 입력입니다.");
			
		}
		
		scanner.close();
	}

}
