package chapter8Exercise;

public class Circle extends Shape{
		//필드
		private double radius;
			
		//생성자
		public Circle(double radius) {
			
			this.radius = radius;
		}
		//메소드
		@Override
		public double area() {
			
			return Math.PI * Math.pow(radius, 2);
		}
		@Override
		public double perimeter() {
			
			return Math.PI * radius * 2;
		}
		@Override
		public String toString() {
		return String.format("도형의 종류 : 원, 둘레: %.2f㎝, 넓이: %.2f㎠", perimeter(), area());
		}
		
}
