package VO;

public class ParkingVO {
	private String prkNo;
	private String prkIptTime;
	private String prkOptTime;
	private String carNo;
	private String psNo;
	private String time;
	private int fee;
	
	public ParkingVO() {}
	public ParkingVO(String psNo) {
		this.psNo = psNo;
	}
	
	public ParkingVO(String carNo, String psNo) {
		this.carNo = carNo;
		this.psNo = psNo;
	}
	
	public ParkingVO(String carNo, String prkIptTime, String prkOptTime, String time, int fee) {
		this.carNo = carNo;
		this.prkIptTime = prkIptTime;
		this.prkOptTime = prkOptTime;
		this.time = time;
		this.fee = fee;
	}
	
	public String getPrkNo() {
		return prkNo;
	}
	public void setPrkNo(String prkNo) {
		this.prkNo = prkNo;
	}
	public String getPrkIptTime() {
		return prkIptTime;
	}
	public void setPrkIptTime(String prkIptTime) {
		this.prkIptTime = prkIptTime;
	}
	public String getPrkOptTime() {
		return prkOptTime;
	}
	public void setPrkOptTime(String prkOptTime) {
		this.prkOptTime = prkOptTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getPsNo() {
		return psNo;
	}
	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}
	
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s, %s",
				prkNo, prkIptTime, prkOptTime, carNo, psNo, time, fee);
	}
	
}
