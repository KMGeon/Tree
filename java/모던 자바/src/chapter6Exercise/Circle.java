package chapter6Exercise;

public class Circle {
	
	//필드
	
	private double radius;
	private double x;
	private double y;
	
	//기본생성자
	
	//메소드
	
	public double getArea() {
		
		return Math.PI * Math.pow(radius, 2);
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		if(radius<0) {
			this.radius = 0;
		}else {
			this.radius = radius;			
		}
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
}
