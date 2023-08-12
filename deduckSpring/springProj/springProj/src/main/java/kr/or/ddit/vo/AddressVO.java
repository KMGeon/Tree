package kr.or.ddit.vo;

//자바빈 클래스
public class AddressVO {
	//회원 아이디
	private String userId;
	private String postCode;
	private String address;
	private String addressDetail;
	
	public AddressVO() {}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddressVO [userId=" + userId + ", postCode=" + postCode + ", address=" + address + ", addressDetail="
				+ addressDetail + "]";
	}

	
	
	
}
