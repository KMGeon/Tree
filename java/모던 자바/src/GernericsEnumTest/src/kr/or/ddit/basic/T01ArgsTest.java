package kr.or.ddit.basic;

public class T01ArgsTest {
	/*
	 *  가변형 인수 => 메서드의 매개변수의 개수가 실행될 때마다 다를 때 사용한다.
	 *  
	 *  - 가변형 인수는 메서드 안에서 배열로 처리된다.
	 *  - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	 *  
	 */
	// 배열을 이용한 메서드 
	// 매개변수로 받은 정수들의 합계를 수하는 메서드(이 정수들의 개수는 상황에 따라 다름)
	public int sumArr(int[] data) { // int 타입의 배열이 들어온다는 의미
		int sum = 0;
		for(int i=0 ; i<data.length ; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수를 이용한 메서드
	public int sumArg(int...data) { // 위와의 차이점 : int값이 들어온다는 의미 길이는 모른다.
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum+= data[i];
		}
		return sum;
	}
	
	// 가변형 인수와 일반적인 인수를 같이 사용할 경우에는
	// 가변형 인수를 제일 뒤쪽에 배치해야한다.
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for(int i = 0; i<data.length; i++) {
			sum+=data[i];
		}
		return name + "씨의 점수: " + sum;
	}
	
	public static void main(String[] args) {
		T01ArgsTest at = new T01ArgsTest();
		
		int[] nums = {100, 200, 300};
		System.out.println(at.sumArr(nums));
		System.out.println(at.sumArr(new int[] {1, 2, 3, 4, 5}));
		System.out.println();
		
		System.out.println(at.sumArg(100, 200, 300));
		System.out.println(at.sumArg(1, 2, 3, 4, 5));
		System.out.println();
		
		System.out.println(at.sumArg2("홍길동", 1,2,3,4,5,6,7,8));
		
	}
	
	
	
}
