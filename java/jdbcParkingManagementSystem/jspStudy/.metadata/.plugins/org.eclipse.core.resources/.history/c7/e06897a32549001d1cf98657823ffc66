package vo;

import java.io.Serializable;

// ProductVO : PRODUCT 테이블의 1 행을 담음
// VO : Value Object
// 자바빈 클래스(멤버변수, 기본 생성자, getter/setter메소드)
// implements Serializable : 직렬화. 인터페이스를 상속받는다(생략 가능)
public class BookVO implements Serializable {
	// 생략 가능
	private static final long serialVersionUID = -4274700572038677000L;

	// 멤버 변수
	private String bookId; // 도서 아이디
	private String name; // 도서명
	private int unitPrice; // 가격
	private String author; // 저자
	private String description; // 설명
	private String publisher; // 출판사
	private String category; // 분류
	private long unitsInStock; // 재고 수
	private long totalPages; // 페이지 수
	private String releaseDate; // 출판일(월/년)
	private String condition; // 신규 도서 or 중고 도서 or E-Book
	private String filename;

	// 기본 생성자
	public BookVO() {
	}

	// 상품 아이디, 상품명, 가격 파라미터들을 매개변수로 받는 생성자 -> 멤버변수에 할당
	public BookVO(String bookId, String name, int unitPrice) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	// getter/setter메소드
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", name=" + name + ", unitPrice=" + unitPrice + ", author=" + author
				+ ", description=" + description + ", publisher=" + publisher + ", category=" + category
				+ ", unitsInStock=" + unitsInStock + ", totalPages=" + totalPages + ", releaseDate=" + releaseDate
				+ ", condition=" + condition + ", filename=" + filename + "]";
	}

}
