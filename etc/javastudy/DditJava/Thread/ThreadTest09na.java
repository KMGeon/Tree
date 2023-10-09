package na;

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

public class ThreadTest09na {
	public static void main(String[] args) {
		Thread th1 = new DataPut();
		Thread th2 = new Count();
		
		th1.start();
		th2.start();
	}
}

//데이터를 입력하는 쓰레드
class DataPut extends Thread{
	//입력 여부를 확인하기 위한 변수 선언
	//쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	public static String str = null;
	@Override
	public void run() {
		str = JOptionPane.showInputDialog("아무거나 입력하세요");
		inputCheck = true; //입력이 완료되면 true로 변경
		System.out.println("입력값 : "+str);
	}
}

//카운트 다운을 진행하는 쓰레드
class Count extends Thread{
	@Override
	public void run() {
		for(int i=5; i>=1; i--){
			System.out.println(i);
			//입력이 완료되었는지 여부를 검사해서 입력이 완료되면 쓰레드를 종료시킨다.
			if(DataPut.inputCheck==true){
				return;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}

//난수발생
public void MakeNum() {

	int num = (int) (Math.random() * 2);

	switch (num) {
	case 0: // 가위		
		System.out.println("-- 결과 --"); 
		System.out.println("컴퓨터 : 가위");
		
		switch(DataPut.str){
		case "가위":
			System.out.println("사용자 : 가위");
			System.out.println("결   과 : 비겼습니다.");
			break;
			
		case "바위":
			System.out.println("사용자 : 바위");
			System.out.println("결   과 : 이겼습니다.");
			break;
			
		case "보":
			System.out.println("사용자 : 보");
			System.out.println("결   과 : 졌습니다.");
			break;
		
		}
		
		break;
		
	case 1: // 바위
		System.out.println("-- 결과 --"); 
		System.out.println("컴퓨터 : 바위");
		
		switch(DataPut.str){
		case "가위":
			System.out.println("사용자 : 가위");
			System.out.println("결   과 : 졌습니다.");
			break;
			
		case "바위":
			System.out.println("사용자 : 바위");
			System.out.println("결   과 : 비겼습니다.");
			break;
			
		case "보":
			System.out.println("사용자 : 보");
			System.out.println("결   과 : 이겼습니다.");
			break;
		
		}
		
		break;
		
	case 2: // 보
		System.out.println("-- 결과 --"); 
		System.out.println("컴퓨터 : 보");
		
		switch(DataPut.str){
		case "가위":
			System.out.println("사용자 : 가위");
			System.out.println("결   과 : 이겼습니다.");
			break;
			
		case "바위":
			System.out.println("사용자 : 바위");
			System.out.println("결   과 : 졌습니다.");
			break;
			
		case "보":
			System.out.println("사용자 : 보");
			System.out.println("결   과 : 비겼습니다.");
			break;
		
		}
		
		break;

		}
	}

}



