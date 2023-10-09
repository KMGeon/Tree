package chapter8Exercise;

public class Novel extends Book{
	//생성자
	public Novel(String title, String author) {
		super(title, author);
	}
	
	//메소드
	@Override
	public int getLateFee(int lateDays) {
		return 10;
	}
	
	
}
