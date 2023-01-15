package dao;

import java.util.ArrayList;
import java.util.List;

import vo.BookVO;

public class BookRepository {
	// 싱글톤 패턴
	// ProductRepository 클래스의 기본 생성자에 대한 객체 변수 instance를 작성한다.
	private static BookRepository instance = new BookRepository();
	// 객체 변수 instance에 대한 Getter() 메소드
	public static BookRepository getInstance() {
		return instance;
	}
	
	// 상품 목록을 저장하기 위한 List<ProductVO> 객체 타입의 변수
	private List<BookVO> listOfBook =
			new ArrayList<BookVO>();
	
	// 기본 생성자
	public BookRepository() {
		// HTML 도서 추가(1행(BookVO) 추가)
		// String bookId, String name, int unitPrice
		BookVO HTML = new BookVO("B1234", "[Hello Coding] HTML5+CSS3", 15000);
		HTML.setDescription("워드나 PPT 문서를 만들 수 있나요? 그러면 문제 없습니다."
				+ "지금 바로 웹페이지 제작에 도전해보세요. 지금 당장 컴퓨터가 없어도 괜찮습니다. 코드와 실행 화면이 바로 보여서 눈으...");
		HTML.setCategory("HTML");
		HTML.setPublisher("한빛미디어");
		HTML.setAuthor("황재호");
		HTML.setUnitsInStock(2000);
		HTML.setTotalPages(200);
		HTML.setReleaseDate("2009.06.02");
		HTML.setFilename("정의란무엇인가.png");
		
		listOfBook.add(HTML);
		
		// 2) java 도서 추가(1행(BookVO) 추가)
		BookVO java = new BookVO("B1235", "[IT모바일] 쉽게 배우는 자바 프로그래밍", 27000);
		java.setDescription("객체 지향의 핵심과 자바의 현대적 기능을 충실히 다루면서도 초보자가 "
				+ "쉽게 학습할 수 있게 구성했습니다. 시각화 도구를 활용한 개념 설명과 군더더기 없는 핵심 코드를 통해 개념과 구현...");
		java.setCategory("JAVA");
		java.setPublisher("한빛아카데미");
		java.setAuthor("우중중");
		java.setFilename("돈의심리학.png");
		
		listOfBook.add(java);
		
		// 3) spring 도서 추가(1행(BookVO) 추가)
		BookVO spring = new BookVO("P1236", "[IT모바일] 스프링4 입문", 27000);
		spring.setDescription("스프링은 단순히 사용 방법만 익히는 것보다 아키텍쳐를 어떻게 이해하고"
				+ " 설계하는지가 더 중요합니다. 예제를 복사해 붙여넣는 식으로는 실제 개발에서 스프링을 제대로 활용할 수 없습니다...");
		spring.setCategory("Spring");
		spring.setPublisher("한빛미디어");
		spring.setAuthor("하세가와 유이치,오오노 와타루, 토키 코헤미(권은철, 전민수)");
		spring.setFilename("태엽감는새.png");
		
		listOfBook.add(spring);
	}
	
	// SELECT * FROM PRODUCT
	// 객체 타입의 변수 listOfProducts 에 저장된 모든 상품 목록을 가져오는 메소드
	public List<BookVO> getAllBook() {
		return listOfBook;
	}
	
	// INSERT INTO PRODUCT VALUES(...)
	// 객체 타입의 변수 listOfProducts에 새로운 상품 정보를 등록하는 메소드
	public void addBook(BookVO bookvo) {
		listOfBook.add(bookvo);
	}
	
	// 상세보기 => 1행(ProductVO)을 리턴
	// SELECT * FROM PRODUCT WHERE PRODUCT_ID = 'P1235'
	public BookVO getBookById(String bookId) {
		// 자바빈객체 선언
		BookVO bookById = null;
		
		// listOfProducts의 크기만큼 반복
		for (int i = 0; i < listOfBook.size(); i++) {
			BookVO vo = listOfBook.get(i);
			if (vo!=null&&vo.getBookId()!=null&&vo.getBookId().equals(bookId)) {
				bookById = vo;
				// 반복문을 빠져나온다
				break;
			}
		}
		
		return bookById;
		
	}
}
