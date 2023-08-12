package kr.or.ddit.vo;

//장바구니 상세 테이블 용 자바빈 클래스
public class CartDetVO {
	//멤버변수
	private String productId;
	private String cartId;
	private int unitPrice;
	private int quantity;
	private int amount;
	private String etc;
	
	//기본 생성자
	public CartDetVO() {}

	//getter/setter메소드
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "CartDetVO [productId=" + productId + ", cartId=" + cartId + ", unitPrice=" + unitPrice + ", quantity="
				+ quantity + ", amount=" + amount + ", etc=" + etc + "]";
	}
	
	
}
