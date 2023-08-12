package chapter5;

import java.util.Random;
import java.util.Scanner;

public class Exercise48 {

	public static void main(String[] args) {
		
		String[] RockScissorsPaper = {"바위", "가위", "보"};
		
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		String rock = "바위";
		String scissors = "가위";
		String Paper = "보";
		
		System.out.print("가위 바위 보를 입력하세요: ");
		String gamer = scanner.nextLine();
		System.out.println("게이머: " + gamer);
		
		int randomNum = random.nextInt(3);
		System.out.println("인공지능 컴퓨터: "+RockScissorsPaper[randomNum]);
		//String computer = RockScissorsPaper[random.nextInt(3)]; -> 22번줄을 안쓰고 
		
		if(gamer.equals(RockScissorsPaper[randomNum])) {
			
			System.out.println("결과: 무승부!");
			
		}else if((RockScissorsPaper[randomNum].equals(rock)&&gamer.equals(Paper))||
				(RockScissorsPaper[randomNum].equals(scissors)&&gamer.equals(rock))||
				(RockScissorsPaper[randomNum].equals(Paper)&&gamer.equals(scissors))) {
			
			System.out.println("결과: 게이머 승리!");
			
		}else {
			
			System.out.println("결과: 인공지능 컴퓨터 승리!");
		}
		
		scanner.close();
	}

}
