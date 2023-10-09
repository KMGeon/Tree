package chapter7Exercise;

public class Circle extends Shape{
		//필드
		private double radius;
			
		//생성자
		public Circle(double radius) {
			super();
			this.radius = radius;
		}
		//메소드
		@Override
		public double area() {
			super.area();
			return Math.PI * Math.pow(radius, 2);
		}
		@Override
		public double perimeter() {
			super.perimeter();
			return Math.PI * radius * 2;
		}

		public String toString() {
		return "도형의 종류: 원, 둘레:, "+ perimeter() +" 넓이: " + area() +"㎠";
		}
}
