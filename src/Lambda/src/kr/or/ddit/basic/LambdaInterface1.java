package kr.or.ddit.basic;

@FunctionalInterface
public interface LambdaInterface1 {
	// 반환값이 없고 매개변수도 없는 추상메소드 선언
	public void test();	
}

@FunctionalInterface
interface LambdaInterface2 {
	// 반환값이 없고 매개변수는 있는 추상메소드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaInterface3 {
	// 반환값이 있고 매개변수도 있는 추상메소드 선언
	public int test(int a, int b);
}
