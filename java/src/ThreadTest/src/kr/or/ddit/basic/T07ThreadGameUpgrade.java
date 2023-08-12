package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class T07ThreadGameUpgrade {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		GameUpgrade gameUpgrade = new GameUpgrade();
		CountDownUpgrade countDownUpgrade = new CountDownUpgrade();
		
		gameUpgrade.start();
		countDownUpgrade.start();
		
	}
}

class GameUpgrade extends Thread {
	
	@Override
	public void run() {
		
		String user = JOptionPane.showInputDialog("가위바위보를 입력하세요!");
		
		String[] rsp = {"가위","바위","보"};
		Random random = new Random();
		int ranNum = random.nextInt(3);
		
		String result =  "";
		
		if(rsp[ranNum].equals(user)) {
			result = "무승부";
		}else if(rsp[ranNum].equals(rsp[0])&&(user.equals(rsp[1]))||
				rsp[ranNum].equals(rsp[1])&&(user.equals(rsp[2]))||
				rsp[ranNum].equals(rsp[2])&&(user.equals(rsp[0]))) {
			result = "당신이 이겼습니다.";
		}else if(rsp[ranNum].equals(rsp[0])&&(user.equals(rsp[2]))||
				rsp[ranNum].equals(rsp[1])&&(user.equals(rsp[0]))||
				rsp[ranNum].equals(rsp[2])&&(user.equals(rsp[1]))) {
			result = "컴퓨터가 이겼습니다.";
		}else {
			result = "똑바로 내주세요!";
		}
		
		T07ThreadGame.inputCheck = true;
		System.out.println("===========결과");
		System.out.println("컴퓨터 : " + rsp[ranNum]);
		System.out.println("당신 : " + user);
		System.out.println("결과: "+ result);
		System.exit(0);
		
	}
	
}

class CountDownUpgrade extends Thread {
	
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