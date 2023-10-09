package VO;

public class PSVO {
	String psNo;
	String psLoc;
	
	public PSVO() {}
	
	public PSVO(String psNo) {
		this.psNo = psNo;
	}

	public PSVO(String psLoc, String psNo) {
		this.psLoc = psLoc;
		this.psNo = psNo;
	}
	
	public String getPsNo() {
		return psNo;
	}
	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}
	public String getPsLoc() {
		return psLoc;
	}
	public void setPsLoc(String psLoc) {
		this.psLoc = psLoc;
	}

	@Override
	public String toString() {
		return String.format("%s, %s", psNo, psLoc);
	}
	
}
