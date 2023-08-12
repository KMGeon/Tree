package kr.or.ddit.basic;


class Util2{
	// Number와 자손들로 타입을 제한했다.
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

public class T05GenericMethodTest {
	public static void main(String[] args) {
		
		int result1 = Util2.<Integer>compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.<Number>compare(3.4, 3);
		System.out.println(result2);
		
//		밑에 코드는 타입이 Number가 아니기 때문에 
//		컴파일 오류로 개발자가 오류를 인지하게 해준다.(런타임오류가 아니라)
//		Util2.compare("C", "JAVA");
		
		
	}
}
