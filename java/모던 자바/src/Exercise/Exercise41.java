package chapter5;

import java.util.Scanner;

public class Exercise41 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("삼각형 첫번째 변의 길이를 입력하세요: ");

		int x = scanner.nextInt();
		
		System.out.print("삼각형 두번째 변의 길이를 입력하세요: ");
		
		int y = scanner.nextInt();
		
		System.out.print("삼각형 세번째 변의 길이를 입력하세요: ");
		
		int z = scanner.nextInt();
		
		if((x>=y && x>=z)&& x<y+z) {
			
			System.out.println("삼각형을 만들 수 있습니다.");
			
		}else if((y>=z && y>=x)&& y<x+z){
			
			System.out.println("삼각형을 만들 수 있습니다.");
			
		}else if((z>=x && z>=y)&& z<x+y){
			
			System.out.println("삼각형을 만들 수 있습니다.");
			
		}	
		
		else {
			
			System.out.println("삼각형을 만들 수 없습니다.");
			
		}
		
		scanner.close();
	}

}
