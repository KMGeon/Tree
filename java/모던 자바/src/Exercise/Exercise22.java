package chapter02;

public class Exercise22 {

	public static void main(String[] args) {
		
		double distance = 40e12;
		double speed = 300000;
		double speedYear = 365*24*60*60* speed;
		
		System.out.println("빛의 속도로 프록시마 센타우리 별까지 가는데 걸리는 시간은 " + distance/speedYear + "광년이다.");
		
		
		
	}

}
