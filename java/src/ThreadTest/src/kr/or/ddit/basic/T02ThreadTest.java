package kr.or.ddit.basic;

public class T02ThreadTest {
	public static void main(String[] args) {
		// 멀티 스레드 프로그램 방식
		
		// 방법1 : Thread클래스를 상속한 class 의 인스턴스를 생성한 후 이 인스턴스의 start() 메소드를 실행한다.
		MyThread1 th1 = new MyThread1(); // 다형성 MyThread1(o), Thread(x)
		th1.start();
		
		// 방법2 : Runnable 인터페이스를 구현한 클래스의 인터페이스를 생성한 후 이 인스턴스를 Thread객체의 
		//		   인스턴스를 생성할 때 생성자의 파라미터로 넘겨준다. 이 때 생성된 Thread객체의 인스턴스의 start() 실행한다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();
		
		// 방법3 : 익명클래스를 이용하는 방법
		//		  Runnable인터페이스를 구현한 익명클래스를 Thread 인스턴스를 생성할 때
		// 		   매개변수로 넘겨준다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<200; i++) {
					System.out.print("↔");
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		});
		
		th3.start();
		
		
	}
}


class MyThread1 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<200; i++) {
			System.out.print("☆");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				//						시간은 밀리세컨드 단위를 사용함.
				//						즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}


class MyThread2 implements Runnable{
	
	@Override
	public void run() {
		for(int i=0; i<200; i++) {
			System.out.print("★");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
