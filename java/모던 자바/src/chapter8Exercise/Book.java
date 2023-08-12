package chapter8Exercise;

public abstract class Book {
	//필드
	private int number;
	private String title;
	private String author;
	private static int countOfBooks = 0;
	
	//생성자
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.number = countOfBooks++;
	}

	//메소드
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public abstract int getLateFee(int lateDays);
	@Override
	public boolean equals(Object object) {
		if(title.equals(author)) {
			return true;
		}else {
			return false;
		}
	}
//	public int hashCode() {
//		
//	}
	public String toString() {
		return String.format("관리번호 %, 제목: %s, 작가: %s(일주일 연체료: %d)",number,title,author,getLateFee(number)); 
	}
	
}
