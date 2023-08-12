package chapter8Exercise;

public class Poet extends Book{
	//생성자
	public Poet(String title, String author) {
		super(title, author);
	}
		
	//메소드
	@Override
	public int getLateFee(int lateDays) {
		return 10;
	}
	
}
