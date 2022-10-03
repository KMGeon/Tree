package Generics;

public class T05GenericMethodTest {
	
	public static void main(String[] args) {
		int result1 = Util2.commpare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.<Number>commpare(3.14, 3);
		System.out.println(result2);
		
		//Util2.commpare("C","JAVA");
	}
}
class Util2{
	public static <T extends Number>int commpare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		
		return Double.compare(v1, v2);
	}
}
