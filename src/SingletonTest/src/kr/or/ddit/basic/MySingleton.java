package kr.or.ddit.basic;

public class MySingleton {
/*
 * 싱글톤패턴 => 객체(인스턴스)를 구성하는 방법
 * 
 * 1. 자기 자신 class의 참조변수를 멤버변수로 선언한다.
 * 	(이 변수는 private static으로 선언한다.)
 * 2. 생성자 private 으로 한다.
 *  (외부에서 생성자에 접근을 못하게 하기 위해서 즉, 외부에서 new 명령을 사용하지 못하게 하기 위해서)
 * 3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메소드를 만든다.
 *  (이름은 보통 getInstance() 로 지정하고, static으로 설정한다.) 
 * */
	
	// 자기 자신의 class의 참조값을 저장하는 멤버변수 선언
	private static MySingleton single;
	
	// 생성자를 private으로 지정한다.
	private MySingleton() {
		System.out.println("생성자입니다.");
	}
	
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		}
		
		return single;
	}
	
	// 나머지 내용들은 이 클래스로 처리할 내용들을 기술한다.
	public void displayText() {
		System.out.println("안녕하세요 싱글톤 객체입니다.");
	}
	
}
