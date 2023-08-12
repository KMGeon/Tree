package chapter5;

public class Example5 {

	public static void main(String[] args) {
		
		int[][] array = {
				
				{95,86},
				{83, 92, 96},
				{78, 83,93, 87, 88}
				
		};
		
		int sum = 0;
		double avg = 0.0;
		
		
		for(int i=0; i<array.length;i++) {
			for(int j=0; j<array[i].length; j++) {
				
				sum += array[i][j];
				
			}
			avg += array[i].length;
		}
		avg = sum / avg;
		
		/*
		int count = 0;
		for문 안에 count++;를 넣어 반복할 때 마다 카운트를 하고
		count는 double로 변환하여 한다
		sum을 count로 나눠준다.
		*/
		
		System.out.println("sum: " + sum);
		System.out.println("avg: " + avg);
	}

}
