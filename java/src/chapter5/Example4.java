package chapter5;

public class Example4 {

	public static void main(String[] args) {
		int max=0;			//Integer.MIN_VALUE;
		int min=0;			//Integer.MAX_VALUE;	배열을 만들기 전에 초기화하려면 이런 표현. 
		int[] array = { 10, 5, 1, 8, 2 };
		min = array[0];		// 배열을 만들고 한다면 첫 번째로 초기화 한 후 비교한다.
		
		for(int i=0; i < array.length; i++) {
			
			if(max<array[i]) {
				
				max = array[i];	
			}
			
			if(min>array[i]) {
				
				min = array[i];	
				
			}
			
		}
		
		//처음 작성한 코드
		
		/*	
		for(int j=0; j < array.length; j++){
				min = array[0]; 
			if(min>array[j]) {
				
				min = array[j];
				
			}
		}
		*/
		
		System.out.println("max: " + max);
		System.out.println("min: " + min);	
	}
}
		// 이런 문제가 많이 쓰이다 보니 함수가 있다. Collection.max(min).