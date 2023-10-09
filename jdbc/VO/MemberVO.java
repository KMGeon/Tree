package VO;

public class MemberVO {
	private String memId;
	private String memPw;
	private String memName;
	private String memTel;
	private String memGd;
	private String carNo;

	public MemberVO() {
	}

	public MemberVO(String memId) {
		this.memId = memId;
	}

	public MemberVO(String memId, String memPw) {
		this.memId = memId;
		this.memPw = memPw;
	}

	public MemberVO(String memId, String memName, String memTel) {
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
	}

	public MemberVO(String memId, String memPw, String memName, String memTel) {
		this.memId = memId;
		this.memPw = memPw;
		this.memName = memName;
		this.memTel = memTel;
	}

	public MemberVO(String memId, String memPw, String memName, String memTel, String carNo) {
		this.memId = memId;
		this.memPw = memPw;
		this.memName = memName;
		this.memTel = memTel;
		this.carNo = carNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemGd() {
		return memGd;
	}

	public void setMemGd(String memGd) {
		this.memGd = memGd;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Override
	public String toString() {
		return String.format("     %-10s%-10s\t %-15s  %-10s\t%s\n", memId, memPw,
				memName, memTel, carNo);
	}

}
