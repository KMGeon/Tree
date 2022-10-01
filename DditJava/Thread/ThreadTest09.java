package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 컴퓨터 가위 바위 보를 진행하는 프로그램을 작성하시오.

 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
 사용자의 가위 바위 보는 showInputDialog()메서드를 이용해서 입력받는다.

 입력시간은 5초로 제한하고 카운트 다운을 한다.
 5초안에 입력이 없으면 게임에 진 것으로 처리한 후 끝낸다.

 5초안에 입력이 있으면 승패를 구해서 출력한다.

 결과 예시)
 1) 5초안에 입력이 완료되었을 때 
 -- 결과 -- 
 컴퓨터 : 가위
 사용자 : 바위
 결   과 : 당신이 이겼습니다.

 1) 5초안에 입력을 못했을 때 
 -- 결과 -- 
 
 시간초과로 당신이 졌습니다.	
 */

public class ThreadTest09 {
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		// 난수를 이용해서 컴퓨터의 가위 바위 보 정하기
		String[] data = {"가위", "바위", "보"}; //index => 0~2
		int index = (int)(Math.random()*3);
		String com = data[index];
		
		// 사용자의 가위 바위 보 입력 받기
		gt.start();
		String man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
		GameTimer.inputCheck = true;
		
		//결과 판정하기
		String result = ""; //결과가 저장될 변수 선언
		if(com.equals(man)){
			result = "비겼습니다";
		}else if((man.equals("가위")&& com.equals("보")) ||
				(man.equals("바위")&& com.equals("가위")) ||
				(man.equals("보")&& com.equals("바위"))){
			result = "당신이 이겼습니다.";
		}else{
			result = "당신이 졌습니다.";
		}
		
		//결과 출력
		System.out.println("--결    과--");
		System.out.println("컴퓨터: " +com);
		System.out.println("사용자: " +man);
		System.out.println("결   과: " +result);
	
	}
	
}

//카운트 다운을 진행하는 쓰레드
class GameTimer extends Thread{
	public static boolean inputCheck = false;
	@Override
	public void run() {
		System.out.println("5초 카운트 다운 시작");
		for(int i=5; i>=1; i--){
			if(inputCheck==true){
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}if(inputCheck==true){
				return;
			}
		}
		System.out.println("--결    과--");
		System.out.println("시간초과로 당신이 졌습니다");
		System.exit(0);
	}
	
}