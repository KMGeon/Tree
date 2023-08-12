package bookRepository;

import java.util.ArrayList;
import java.util.List;

import bookRepository.BookVO;

public class BookRepository {
	
	private List<BookVO> listOfBooks = new ArrayList<BookVO>();
	//싱글톤패턴
	private static BookRepository brp = new BookRepository();
	public  static BookRepository getInstance() {
		if(brp == null) {
			brp = new BookRepository();
		}
		return brp;
	}
	private BookRepository() {
		BookVO js = new BookVO("P101", "HTML5+CSS3", 15000);
		js.setDescription("워드나 PPT문서를 만들 수 있나요??");
		js.setAuthor("황재호");
		js.setPublisher("한빛미디어");
		js.setCategory("Hello Coding");
		
		listOfBooks.add(js);
		
		BookVO java = new BookVO("P102", "쉅게 배우는 자바 프로그래밍", 27000);
		java.setDescription("객체 지향의 핵심과 자바의 현대적 기능을 충실히 다루면서도 초보자가 쉽게 학습 할 수 있게 구성했습니다.");
		java.setAuthor("우종중");
		java.setPublisher("한빛아카데미");
		java.setCategory("IT모바일");
		
		listOfBooks.add(java);
	
		BookVO spring = new BookVO("P103", "스프링4 입문", 27000);
		spring.setDescription("스프링은 단순히 사용 방법만 익히는 것보다 아키텍처를 어떻게 이해라고 설계하는지가 더 중요합니다.");
		spring.setAuthor("하세가와 유이치, 모모노 와타루, 토키 코헤이[권은철, 전민수]");
		spring.setPublisher("한빛미디어");
		spring.setCategory("IT모바일");
		
		listOfBooks.add(spring);
	}
	
	
	
	//SELECT * FROM BOOK;
	public List<BookVO> getAllBooks() {
		return listOfBooks;
	}
	//SELECT * FROM BOOK WHERE BOOK_ID = 'P101';
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
}
