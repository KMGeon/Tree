package chapter7Exercise;

public class Triangle extends Shape{
		//필드
		private double side;
		
		//생성자
		public Triangle(double side) {
			super();
			this.side = side;	
			
		}
		//메소드
		@Override
		public double area() {
			super.area();
			return (Math.sqrt(3)/4) * Math.pow(side, 2) ;
		}
		@Override
		public double perimeter() {
			super.perimeter();
			return side*3;
		}
	
		public String toString() {
			return "도형의 종류: 삼각형, 둘레:, "+ perimeter() +" 넓이: " + area() +"cm";
		}
}
