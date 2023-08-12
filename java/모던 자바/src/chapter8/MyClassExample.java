package chapter8;

public class MyClassExample {

	public static void main(String[] args) {
		MyClass class1 = new MyClass();
		class1.control.tunrOn();
		class1.control.setVolume(5);
		
		MyClass class2 = new MyClass(new Audio());
		
		MyClass class3 = new MyClass();
		class3.methodA();
		
		MyClass class4 = new MyClass(new Audio());
		class4.methodB();
		
		MyClass class5 = new MyClass(new SmartTelevision());
		class5.methodB();
		
		MyClass class6 = new MyClass();
		
	}

}
