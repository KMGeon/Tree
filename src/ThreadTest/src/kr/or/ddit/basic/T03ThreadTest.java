package kr.or.ddit.basic;

public class T03ThreadTest {
	public static void main(String[] args) {
		
		// 방법 2번 사용 new MyRunner()를 Runnable 타입으로 담지 않고 바로 생성 후 
		// 생성자의 파라미터로 넘겨준다.
		// 스레드 수행시간 테스트
		Thread th = new Thread(new MyRunner());
		
		// UTC(Universal Time Coodinated 협정 세계 표준시)를 사용하여
		// 1970년 1월1일0시0분0초를 기준으로 경과한 시간을 밀리세컨드 단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		
		th.start(); // 스레드 구동하기
		
		try {
			th.join(); // 현재 실행중인 스레드에서 작업중인 스레드(지금은 th스레드)가
					   // 종료될 때까지 기다린다. 쓰는 이유 끝날 때까지 기다려서 시간차이를 알려고.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간(ms) : " + (endTime - startTime));
		
	}
}

// 1~1000000000 까지 합계를 구하는 스레드 클래스 
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(int i=1; i<=1000000000; i++) {
			sum +=i;
		}
		System.out.println("합계 : " + sum);
		
		
	}
	
	
	
	
}