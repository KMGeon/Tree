package chapter8Exercise;

public class BookTest {

	public static void main(String[] args) {
		Book[] books = new Book[6];
		books[0] = new Novel("칼의 노래", "김훈");
		books[1] = new Novel("칼의 노래", "김훈");
		books[2] = new Poet("이상한 가역반응", "이상");
		books[3] = new Poet("하늘과 바람과 별과 시", "윤동주");
		books[4] = new ScienceFiction("유년기의 끝", "아서.c.클라크");
		books[5] = new ScienceFiction("스페이스 오디세이", "아서.c.클라크");
		for(Book b : books) {
			System.out.println(b);
		}
		System.out.println("1번 책과 2번책은 같은 책인가요?" + books[0].equals(books[1]));
	}

}
