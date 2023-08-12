package chapter6Exercise;

public class Time {
	//필드
	private int hour; 
	private int minute; 
	private int second;
	//생성자
	public Time(){
	}
	public Time(int hour, int minute, int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;			
		
		if(hour>=24 || 0>hour) {
			this.hour = 0;
		}
		if(minute>=60 || 0>minute) {
			this.minute = 0;
		}
		if(second>=60 || 0>second) {
			this.second = 0;
		}
		
			
	}
	//메소드
	 public String toString() {
		return String.format("%02d:%02d:%02d", hour,minute,second);
	 }
	
	
}
