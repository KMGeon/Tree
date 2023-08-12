package chapter8Exercise;

public class ScienceFiction extends Book{
	//생성자
	public ScienceFiction(String title, String author) {
		super(title, author);
	}
		
	//메소드
	public int getLateFee(int lateDays) {
		return 10;	
	}
	
}
