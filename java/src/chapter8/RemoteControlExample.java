package chapter8;

public class RemoteControlExample {

	public static void main(String[] args) {
		RemoteControl rc = new Television();
		rc.tunrOn(); // Television메소드가 실행
		rc = new Audio();
		rc.setVolume(9); // Audio메소드가 실행
		
	}

}
