package kr.or.ddit.basic;

//yield메서드 사용하기
public class ThreadTest11 {
	
	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		
		}
		System.out.println("111111111-------------------------------11111111111");
		th1.work = false;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		
		}
		System.out.println("2222222---------------------------------222222222222");
		th1.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
		}
		System.out.println("33333333---------------------------------33333333333");
		
		th1.stop = true;
		th2.stop = true;
		
	}
}

//yield()메서드 연습용 쓰레드
class YieldThread extends Thread{
	public boolean stop = false;
	public boolean work = true;
	
	public YieldThread(String name) {
		super(name);//쓰레드의 이름 설정하기
	}
	
	@Override
	public void run() {
		while(!stop){//stop값이 true이면 반복문 탈출
			if(work){
				//getName() 메서드 => 쓰레드 이름(name속성값) 반환
				System.out.println(getName()+" 작업중...");
			}else{
				System.out.println(getName()+" 양보...");
				Thread.yield();
			}
			
		}
	}
}
