package chapter8;

public class MyClass {
	RemoteControl control;
	
	public MyClass() {
		control = new Television();
	}
	public MyClass(RemoteControl control) {
		this.control = control;
		control.tunrOn();
		control.setVolume(5);		
	}
	
	void methodA() {
		RemoteControl control = new Audio();
		control.tunrOff();
		control.setVolume(5);
	}
	void methodB() {
		RemoteControl contro2 = new Television();
		control.tunrOff();
		control.setVolume(5);
	}
	
	void method3(RemoteControl control) {
		RemoteControl contro3 = new Television();
		control.tunrOff();
		control.setVolume(5);		
	}
}
