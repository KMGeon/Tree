package chapter8Exercise;

public class Triangle extends Shape{
		//필드
		private double side;
		
		//생성자
		public Triangle(double side) {
			this.side = side;	
			
		}
		//메소드
		@Override
		public double area() {
			
			return (Math.sqrt(3)/4) * Math.pow(side, 2) ;
		}
		@Override
		public double perimeter() {
			
			return side*3;
		}
		@Override
		public String toString() {
			return String.format("도형의 종류 : 정삼각형, 둘레: %.2f㎝, 넓이: %.2f㎠", perimeter(),area());
		}
}
