package chapter6Exercise;

public class PlaneTest {

	public static void main(String[] args) {
		
		Plane plane1 = new Plane("보잉", "보잉 747", 450);
		System.out.println("제조사: " + plane1.getManufacture());
		System.out.println("모델명: " + plane1.getModel());
		System.out.println("최대 승객수: " + plane1.getMaxNumberOfPassengers());
		System.out.println();
		
		Plane plane2 = new Plane("보잉", "보잉 777", 500);
		System.out.println("제조사: " + plane2.getManufacture());
		System.out.println("모델명: " + plane2.getModel());
		System.out.println("최대 승객수: " + plane2.getMaxNumberOfPassengers());
		System.out.println();
		
		Plane plane3 = new Plane();
		plane3.setManufacture("록히드 마틴");
		plane3.setModel("F-22");
		plane3.setMaxNumberOfPassengers(-10);
	
		System.out.println("제조사: " + plane3.getManufacture());
		System.out.println("모델명: " + plane3.getModel());
		System.out.println("최대 승객수: " + plane3.getMaxNumberOfPassengers());
		System.out.println();
		
		System.out.println("생산된 비행기의 수: " + Plane.getNumberOfPlanes());
		
	}

}
