package kr.or.ddit.basic;

public class ThreadTest05 {
	public static void main(String[] args) {
		Thread th1 = new UpperThread();
		Thread th2 = new LowerThread();

		//우선 순위 변경하기 - start()메서드 호출 전에 설정한다
		th1.setPriority(6);
		th2.setPriority(8);
		
		System.out.println("th1의 우선순위 :"+th1.getPriority());
		System.out.println("th2의 우선순위 :"+th2.getPriority());
		
		th1.start();
		th2.start();
		
	}
}

//대문자를 출력하는 쓰레드
class UpperThread extends Thread{
	@Override
	public void run() {
		for(char c='A'; c<='Z'; c++){
			System.out.println(c);
			
			//아무작업도 안하는 반복문(시간 때우기용)
			for(long i=1L; i<1_000_000_000L; i++){ }
			//sleep메서드를 사용해서 멈추게 하면 뭔가 별로라서
 		}
	}
}
//소문자를 출력하는 쓰레드
class LowerThread extends Thread{
	@Override
	public void run() {
		for(char c='a'; c<='z'; c++){
			System.out.println(c);
			
			//아무작업도 안하는 반복문(시간 때우기용)
			for(long i=1L; i<1_000_000_000L; i++){ }
			//sleep메서드를 사용해서 멈추게 하면 뭔가 별로라서
 		}
	}
}

