package kr.or.ddit.basic;

/*
	wait(), notify()를 이용해서 두 쓰레드가 번갈아 한번씩 실행하는 예제
	
	wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능하다.
 */

public class ThreadTest19 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}

}

// 공통으로 사용할 객체
class WorkObject{
	public synchronized void methodA(){
		System.out.println("methodA() 메서드 앞쪽 실행 중...");
		
		notify();
		
		try{
			wait();
		} catch(InterruptedException e){
			
		}
		System.out.println("methodA() 메서드 뒤쪽 실행 중...");
	}
	
	public synchronized void methodB(){
		System.out.println("methodB() 메서드 앞쪽 작업 실행 중...");
		
		notify();
		
		try{
			wait();
		} catch(InterruptedException e){
			
		}
		System.out.println("methodB() 메서드 뒤쪽 작업 실행 중...");
	}
}

// WorkObject의 methodA()메서드를 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;

	//생성자
	public ThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			workObj.methodA();
		}
		synchronized(workObj){
			workObj.notify();
		}
	}
}

//WorkObject의 methodB()메서드를 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;

	//생성자
	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			workObj.methodB();
		}
		synchronized(workObj){
			workObj.notify();
		}
	}
}
