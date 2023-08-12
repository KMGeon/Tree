package kr.or.ddit.basic;

public class Service {

	@PrintAnnotation() // () 생략가능
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "%") // value라는 것이 하나만 있으므로 'value ='생략가능
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "#", count = 25)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
	
	
	
}
