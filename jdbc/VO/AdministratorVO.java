package VO;

public class AdministratorVO {
	private String adminId;
	private String pw;
	private String tel;
	private String carNo;

	public AdministratorVO(String adminId, String pw, String tel, String carNo) {
		super();
		this.adminId = adminId;
		this.pw = pw;
		this.tel = tel;
		this.carNo = carNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s]", adminId, pw, tel, carNo);
	}
}
