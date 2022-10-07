package ch04.dao;

import java.util.ArrayList;
import java.util.List;

import ch04.vo.BookVO;


public class BookRepository {
	//싱글톤
	private static BookRepository instance = new BookRepository();
	public static BookRepository getInstance() {
		return instance;
	}
	private List<BookVO> listOfBooks = new ArrayList<BookVO>();
	
	//기본생성자
	public BookRepository() {
		BookVO book1 = new BookVO("Num1", "[Hello Coding] HTML5+CSS", 15000);
		book1.setDescription("워드나 PPT문서를 만들수 있나요? 그러면 문제 없습니다. 지금 바로 웹페이지에 도전하세요.");
		book1.setCategory("Hello Coding");
		book1.setPublisher("한빛미디어");
		book1.setAuthor("황재호");

		listOfBooks.add(book1);

		BookVO book2 = new BookVO("Num2", "[IT모바일] 쉽게 배우는 자바 프로그래밍", 27000);
		book2.setDescription("객체 지향의 핵심과 자바의 현대적 기능을 다루면서 초보자가 쉽게 학습할 수 있습니다.");
		book2.setCategory("IT모바일");
		book2.setPublisher("한빛아카데미");
		book2.setAuthor("우종중");

		listOfBooks.add(book2);

		BookVO book3 = new BookVO("Num3", "[IT모바일] 스프링4 입문", 27000);
		book3.setDescription("스프링은 단순히 사용 방법만 익히는것보다 아키텍쳐를 이해하는게 중요합니다!");
		book3.setCategory("IT모바일");
		book3.setPublisher("한빛미디어");
		book3.setAuthor("하세가와 유이치,오오노 와타루,토키 코헤이(권은철,전민수)");

		listOfBooks.add(book3);
	}
	
	public List<BookVO> getAllBooks(){
		return listOfBooks;
	}
	public BookVO getBookById(String bookId) {
	
		BookVO bookById = null;
		
		for(int i=0; i < listOfBooks.size(); i++) {
			BookVO vo =listOfBooks.get(i);
			if(vo != null && bookId != null && bookId.equals(vo.getBookId())) {
				bookById = vo;
			}
			
		}
		
		return bookById;
	}
	public void addBook(BookVO bookVO) {
		listOfBooks.add(bookVO);
	}
}
