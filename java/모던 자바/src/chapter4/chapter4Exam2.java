package ddit;

public class chapter4Exam2 {

	public static void main(String[] args) {
		
		int sum = 0;
		for(int num=1; num<=100; num++) {
			
			if(num%3==0) {
				
				sum +=num;
				
			}
			
		}
		System.out.println("3의 배수의 총합은"+ sum + "이다.");
	}

}
