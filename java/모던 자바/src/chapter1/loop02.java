package chapter01;

public class loop02 {
	public static void main(String[] args) {
		//과목 : kor, eng
		int[][] jumsuArr = {
				{90,80},{95,75},{100,90},{95,85},{80,100}
		};
		
		//while문을 사용하여 다음과 같이 출력해보자
		//kor의 평균 : 90
		//eng의 평균 : 90
		
		int i = 0;
		int j = 0;
		int kAvg = 0;
		int eAvg = 0;
		while(i<jumsuArr.length) {
			while(j<jumsuArr[i].length) {
				if(j==0) {
					kAvg += jumsuArr[i][j];
				}else {
					eAvg += jumsuArr[i][j];
				}
				j++;
			}
			j=0;
			i++;
		}
		System.out.println("kor의 평균 : " + kAvg/jumsuArr.length);
		System.out.println("eng의 평균 : " + eAvg/jumsuArr.length);
	}
}
