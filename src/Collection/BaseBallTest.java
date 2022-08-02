package na;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 	(컴퓨터의 숫자는 난수를 이용하여 구한다.)
 	(스트라이크는 S, 볼은 B로 나타낸다.)
 	
 예시) 컴퓨터의 난수 ==> 9 5 7
 
 실행 예시) 숫자입력 => 3 5 6
 		  3 5 6 => 1S 0B
 		  숫자입력 => 7 8 9
 		  7 8 9 => 0S 2B
 		  숫자입력 => 9 7 5
 		  9 7 5 => 1S 2B
 		  숫자입력 => 9 5 7
 		  9 5 7 => 3S 0B
 		  
 		  축하합니다
 		  당신은 4번만에 맞췄습니다.
 */


public class BaseBallTest {

	public static void main(String[] args) {

		ArrayList userList = new ArrayList<>();
		HashSet<Integer> bg = new HashSet<>();

		// 1. 컴퓨터 난수 발생시키기
		while (bg.size() < 3) {
			bg.add((int) (Math.random() * 9 + 1));
		}
		ArrayList comList = new ArrayList<>(bg);
		System.out.println(comList);

		// 2. 숫자입력받기
		Scanner sc = new Scanner(System.in);
		int count = 0;
		
		while (true) {
			count++;
			
			System.out.println("1~9까지의 수중 예상되는 야구게임 수를 중복되지 않게 입력하세요.");

			System.out.println("예상한 야구게임 숫자 중 첫번째 수를 입력하세요");
			int num1 = Integer.parseInt(sc.nextLine());

			System.out.println("예상한 야구게임 숫자 중 두번째 수를 입력하세요");
			int num2 = Integer.parseInt(sc.nextLine());

			System.out.println("예상한 야구게임 숫자 중 세번째 수를 입력하세요");
			int num3 = Integer.parseInt(sc.nextLine());

			if ((0 < num1 && num1 < 10) && (0 < num2 && num2 < 10)
					&& (0 < num3 && num3 < 10) && (num1 != num2)
					&& (num2 != num3) && (num1 != num3)) {
				
				userList = new ArrayList<>();
				userList.add(num1);
				userList.add(num2);
				userList.add(num3);

				System.out.println("입력하신 수는 " + userList + "입니다");
				System.out.println();

			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
				System.out.println();
			}

			// 3. 발생된 난수와 입력한 수 비교하여 판단하기

			int ball = 0;
			int strike = 0;
			
			
			// 자리비교, 숫자비교

			for (int i = 0; i < comList.size(); i++) {
				for (int j = 0; j < userList.size(); j++) {
					if (comList.get(i) == userList.get(j)) {
						if (i == j) {
							strike++;
						} else {
							ball++;

						}

					}

				}

			}

			System.out.println("볼 : " + ball + " 스트라이크 : " + strike);
			if(strike==3){
				System.out.println(count + "번 만에 정답입니다!");
				System.exit(0);
			}
		}
	
	}
}


