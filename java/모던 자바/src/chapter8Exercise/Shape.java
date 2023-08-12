package chapter8Exercise;

public abstract class Shape implements Comparable<Shape>{
	//필드
	
	//생성자
	
	//메소드
	public abstract double area(); 
	public abstract double perimeter();
	
	public String toString() {
		return "shape [둘레=" + area() + ", 넓이" + perimeter() + "]";
	}
	public int compareTo(Shape o) {
		if(this.area()>o.area()) {
			return 1;
		}else if(this.area()<o.area()) {
			return -1;
		}else {
			return 0;
		}
	}
	
}
