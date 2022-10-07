package bookRepository;

public class BookVO {
	
	private String bookId; // 도서 아이디
	private String name; // 도서명
	private Integer unitPrice; // 가격
	private String author; // 저자
	private String description; // 설명
	private String publisher; // 출판사
	private String category; // 분류
	private long unitsInStock; // 재고수
	private long totalPages; // 페이지수
	private String releaseDate; // 출판일(월/년)
	private String condition; // 신규 도시 or 중고 도서 or E-Book
	
	// 기본 생성자
	public BookVO() {

	}
	
	public BookVO(String bookId, String name, Integer unitPrice) {
		
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
		
	}
	
	// getter / setter 메소드
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

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
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

	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", name=" + name + ", unitPrice=" + unitPrice + ", author=" + author
				+ ", description=" + description + ", publisher=" + publisher + ", category=" + category
				+ ", unitsInStock=" + unitsInStock + ", totalPages=" + totalPages + ", releaseDate=" + releaseDate
				+ ", condition=" + condition + "]";
	}
	
	
}
