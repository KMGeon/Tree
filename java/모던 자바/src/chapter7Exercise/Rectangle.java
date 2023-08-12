package chapter7Exercise;

public class Rectangle extends Shape{
	//필드
	private double width;
	private double height;
	//생성자
	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	//메소드
	@Override
	public double area() {
		super.area();
		return width * height;
	}
	@Override
	public double perimeter() {
		super.perimeter();
		return (width+height)*2;
	}
	@Override
	public String toString() {
		return "도형의 종류: 사각형, 둘레:, "+ perimeter() +" 넓이: " + area() +"㎠";
	}

}
