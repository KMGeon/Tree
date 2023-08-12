package chapter8;

public interface RemoteControl {
	int MAX_VOLUME = 10;
	int MIN_VOLUME = 0;
	
	public void tunrOn();
	public void tunrOff();
	public void setVolume(int volume);
	
	default void run() {
		System.out.println("새로 만들어진 인터페이스의 default method");
	}
	
	
}
