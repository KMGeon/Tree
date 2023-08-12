package chapter8;

//추상클래스 : 클래스 -> 상속(extends)
//인터페이스 : 클래스 -> 구현(implements)
public class Television implements RemoteControl{
	private int volume;
	
	@Override
	public void tunrOn() {
		System.out.println("TV를 켭니다.");
	}
	@Override
	public void tunrOff() {
		System.out.println("TV를 끕니다.");
	}
	@Override
	public void setVolume(int volume) {
		if (volume>RemoteControl.MAX_VOLUME) {
			this.volume=RemoteControl.MAX_VOLUME; 
		}else if(volume<RemoteControl.MIN_VOLUME) {
			this.volume=RemoteControl.MIN_VOLUME;
		}else {
			this.volume = volume;
		}
		System.out.println("현재 TV 볼륨: " + this.volume);
	}
}
