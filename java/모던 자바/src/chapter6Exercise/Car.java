package chapter6Exercise;

public class Car {
	
	//필드
	
	private double speed;
	private String color;
	private static final double MAX_SPEED=200;
	
	//생성자
	
	public Car(){
		
	}
	public Car(String color){
		this.color = color;
	}
	
	//메소드
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean speedUp(double speed) {
		if(speed>MAX_SPEED || speed<0) {
			this.speed = 0;
			return false;
		}else {
			this.speed += speed;
			return true;
		}
		
	}
	public static double getMaxSpeed() {
		return MAX_SPEED;
	}

	
	
	
	
}
