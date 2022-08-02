package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		//멀티 쓰레드 프로그램
		
		//Thread를 사용하는 방법
		
		//방법1
		//=> Thread클래스를 상속한 class를 작성한 후 이 class의 인스턴스를 생성한 후 
		// 	 이 인스턴스의 start()메서드를 호출해서 실행한다.
		MyThread1 th1 = new MyThread1(); //인스턴스 생성하기
		th1.start(); //쓰레드 실행하기
		
		//방법2
		//=> - Runnable인터페이스를 구현한 class를 작성한다.
		//	 - 이 class의 인스턴스를 생성한다.
		//   - Thread클래스의 인스턴스를 생성할 때 생성자의 인수값으로 이 class의 인스턴스를 넣어준다.
		//   - Thread클래스의 인스턴스의 start()메서드를 호출해서 실행한다.
		
//		MyRunner1 r = new MyRunner1();
//		Thread th2 = new Thread(r);
		Thread th2 = new Thread(new MyRunner1());
		th2.start();
		
		//방법3 => 익명구현체를 이용하는 방법
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int j=1; j<=200; j++){
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					
					}
				}
			}
		});
		
		th3.start();
		
		System.out.println("main() 메서드 끝");
	}

}

//방법1 - Thread클래스 상속하기
class MyThread1 extends Thread{
	@Override
	public void run(){
		//이 run() 메서드 안에는 쓰레드에서 처리할 내용을 기술한다.
		for(int i=1; i<=200; i++){
			System.out.print("*");
			try{
				//Thread.sleep(시간); => 주어진 '시간'동안 잠시 멈춘다.
				//		'시간'은 밀리세컨드 단위를 사용한다.
				// 		즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			}catch(InterruptedException e){
				
			}
		}
	}
}

//방법2 - Runnable인터페이스 구현하기
class MyRunner1 implements Runnable{
	@Override
	public void run(){
		for(int j=1; j<=200; j++){
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			
			}
		
		}
	}
	
}