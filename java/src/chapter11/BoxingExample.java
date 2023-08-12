package chapter11;

public class BoxingExample {

	public static void main(String[] args) {
	//	Integer i = new Integer(100);
	//	Integer i = 100; // autoboxing 
	//	int i2 = i.intValue();
		
	//	System.out.println(i2);
		
		long startTime = System.nanoTime();
		Integer sum = 0;
		for(int i = 1; i <= 10000; i++ ) {
			sum +=i;
		}
		long endTime = System.nanoTime();
		System.out.println("객체 사용: " + sum);
		System.out.println("객체 사용: " + (endTime - startTime));
		
		long startTime1 = System.nanoTime();
		int sum1 = 0;
		for(int i = 1; i <= 10000; i++ ) {
			sum1 +=i;
		}
		long endTime1 = System.nanoTime();
		System.out.println("기본타입 사용: " + sum1);
		System.out.println("기본타입 사용: " + (endTime1 - startTime1));
		
		//시간 차이가 나는 이유 참조타입의 sum을 기본타입으로 언박싱 후 연산 그리고 박싱
		//wrapper클래스의 장점 .을 찍어 함수를 사용가능
		//하지만 이처럼 성능차이가 날 수 있다는 걸 인지하자
		
	}

}
