package kr.or.ddit.basic;

public class T15SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkThread th1 = new WorkThread("1번스레드", sObj);
		WorkThread th2 = new WorkThread("2번스레드", sObj);
		
		th1.start();
		th2.start();
		
		
	}
}

// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	// 임계영역이 된 이유 공유객체를 동시에 바라보기 때문에
	
	// 동기화 하는 방법1 : 메소드 자체에 동기화 설정하기
//	synchronized public void add() {
	
	// 동기화 하는 방법2 : 동기화 블럭으로 설정하기 (감싸기)
	// (동기화는 줄을 세워 한줄로 들어가는 개념이기 때문에 속도가 느려진다.(동기화를 최소화 하는게 좋다.))
	public void add() {
		//synchronized (this) {
			for(int i=0; i<1000000000; i++) {} // 동기화 전까지 시간벌기용
		
			int n = sum;
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계: " + sum);
		//}

	}
}

// 작업을 수행하는 스레드 클래스
class WorkThread extends Thread {
	private ShareObject sObj;

	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			// 동기화 하는 방법3
			synchronized (sObj) {
				sObj.add();
			}
				
		}
	}
	
}