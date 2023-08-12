package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class T07ThreadGame {
	
	public static boolean inputCheck = false;
	public static int ranNum;
	
	public static void main(String[] args) {
		
		User user = new User();
		CountDown1 countDown = new CountDown1();
		
		user.start();
		countDown.start();
		
	}
}

class User extends Thread {
	
	@Override
	public void run() {
		
		String user = JOptionPane.showInputDialog("가위바위보를 입력하세요!");
		
		String[] rsp = {"가위","바위","보"};
		Random random = new Random();
		T07ThreadGame.ranNum = random.nextInt(3);
		
		System.out.println("========결과");
		
		if(rsp[T07ThreadGame.ranNum].equals(user)) {
			T07ThreadGame.inputCheck = true;
			System.out.println("컴퓨터 : " + rsp[T07ThreadGame.ranNum]);
			System.out.println("당신 : " + user);
			System.out.println("결과: 무승부.");
			System.exit(0);	
		}else if(rsp[T07ThreadGame.ranNum].equals(rsp[0])&&(user.equals(rsp[1]))||
				rsp[T07ThreadGame.ranNum].equals(rsp[1])&&(user.equals(rsp[2]))||
				rsp[T07ThreadGame.ranNum].equals(rsp[2])&&(user.equals(rsp[0]))) {
			T07ThreadGame.inputCheck = true;
			System.out.println("컴퓨터 : " + rsp[T07ThreadGame.ranNum]);
			System.out.println("당신 : " + user);
			System.out.println("결과: 당신의 승리.");
			System.exit(0);	
		}else if(rsp[T07ThreadGame.ranNum].equals(rsp[0])&&(user.equals(rsp[2]))||
				rsp[T07ThreadGame.ranNum].equals(rsp[1])&&(user.equals(rsp[0]))||
				rsp[T07ThreadGame.ranNum].equals(rsp[2])&&(user.equals(rsp[1]))) {
			T07ThreadGame.inputCheck = true;
			System.out.println("컴퓨터 : " + rsp[T07ThreadGame.ranNum]);
			System.out.println("당신 : " + user);
			System.out.println("결과: 컴퓨터의 승리.");
			System.exit(0);	
		}else {
			T07ThreadGame.inputCheck = true;
			System.out.println("컴퓨터 : " + rsp[T07ThreadGame.ranNum]);
			System.out.println("당신 : " + user);
			System.out.println("똑바로 내주세요!");
			System.exit(0);	
		}
		
	
	}
	
}

class CountDown1 extends Thread {
	
	@Override
	public void run() {
		
		for(int i = 5; i>=1; i--) {
			
			if(T07ThreadGame.inputCheck) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("5초가 지났습니다. 입력이 없어 당신의 패배입니다.");
		System.exit(0);	
	}
}


// 문제1
//컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
//컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
//사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력
//받는다.
//입력시간은 5초로 제한하고 카운트 다운을 진행한다.
//5초안에 입력이 없으면 게임을 진 것으로 처리한다.
//5초안에 입력이 완료되면 승패를 출력한다.
//결과예시)
//=== 결과
//컴퓨터 : 가위
//당신: 바위
//결과: 당신이 이겼습니다.