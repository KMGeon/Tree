package chapter01;

public class Hello {

	public static void main(String[] args) {
//		for(int i=1; i<10; i++) {
//			for(int j=1; j<10; j++) {
//				System.out.println( i * j);
//				
//			}
//			
//		}
		int x = 1;
		int y = 1;
		int z = 1;
		while(x<10) {
			System.out.println(x + "단");
			while(y<10) {
				System.out.println(x+ " * " + y + " = " + x*y);
				y++;
			}
			x++;
			y=z;	
		}
	}

}
