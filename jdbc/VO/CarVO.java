package VO;

public class CarVO {
	private String carNo;
	private String memId;
	
	public CarVO() {}
	public CarVO(String carNo) {
		this.carNo = carNo;
	}
	public CarVO(String carNo, String memId) {
		this.carNo = carNo;
		this.memId = memId;
	}
	
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", carNo, memId);
	}
	
}
