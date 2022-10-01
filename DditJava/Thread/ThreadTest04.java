package kr.or.ddit.basic;

/*
	1 ~ 20억까지의 합계를 구하는 프로그램 작성하기
	- 이 작업을 하나의 쓰레드가 단독으로 처리하는 경우와
	    여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해 본다.
 */
public class ThreadTest04 {

	public static void main(String[] args) {
		//단독으로 처리할 쓰레드 생성
		SumThread sm = new SumThread(1L,2_000_000_000L);
		
		//협력해서 처리할 쓰레드 생성(4개의 쓰레드 객체 생성)
		SumThread[] smArr = new SumThread[]{
			new SumThread(	   		  1L, 	500_000_000L),	
			new SumThread(  500_000_001L, 1_000_000_000L),	
			new SumThread(1_000_000_001L, 1_500_000_000L),	
			new SumThread(1_500_000_001L, 2_000_000_000L),	
		};
	
		//단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리할 때의 경과 시간 : "+(endTime-startTime));
		System.out.println();
		System.out.println();
		
		//여러 쓰레드가 협력해서 처리하는 경우
		startTime = System.currentTimeMillis();
		for(SumThread s : smArr){
			s.start();
		}
		for(int i=0; i<smArr.length; i++){
			try {
				smArr[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("협력해서 처리할 때의 경과 시간 : "+(endTime-startTime));
	}

}

class SumThread extends Thread{
	//합계를 구할 영역의 시작값과 종료값이 저장될 변수 선언
	private long min, max;
	
	public SumThread(long min, long max){
		this.min = min;
		this.max = max;
	}
	@Override
	public void run() {
		long sum = 0L;
		for(long i = min; i<=max; i++){
			sum += i;
		}
		System.out.println("합계 : "+sum);
	}
	
	
	
	
}