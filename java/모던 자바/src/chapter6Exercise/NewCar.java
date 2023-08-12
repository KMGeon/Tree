package chapter6Exercise;

public class NewCar {
	
		//필드
	
		private double speed;
		private String color;
		static final double MAX_SPEED=200;
		
		//생성자
		
		public NewCar(){
			
		}
		public NewCar(String color){
			this.color = color;
		}
		
		//메소드
		
		public double getSpeed() {
			return speed;
		}
		public void setSpeed(double speed) {
			this.speed = mileToKillo(speed);
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public boolean speedUp(double speed) {
			if(speed>200 || speed<0) {
				this.speed = 0;
				return false;
			}else {
				this.speed += speed;
				return true;
			}
			
		}
		static double getMaxSpeed() {
			return MAX_SPEED;
		}		
		private static double killoToMile(double killo) {
			return killo / 1.6;
		}
		private static double mileToKillo(double mile) {
			return mile * 1.6;
		}
	
		
}
