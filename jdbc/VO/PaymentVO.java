package VO;

public class PaymentVO {
	private String payNo;
	private String carNo;
	private String iptTime;
	private String optTime;
	private int payFee;

	public PaymentVO() {}

	public PaymentVO(String carNo, String prkiptTime, String prkOptTime, int payFee) {
		this.carNo = carNo;
		this.iptTime = prkiptTime;
		this.optTime = prkOptTime;
		this.payFee = payFee;
	}
	
	
	public PaymentVO(String payNo, String carNo, String iptTime, String optTime, int payFee) {
		this.payNo = payNo;
		this.carNo = carNo;
		this.iptTime = iptTime;
		this.optTime = optTime;
		this.payFee = payFee;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getIptTime() {
		return iptTime;
	}

	public void setIptTime(String iptTime) {
		this.iptTime = iptTime;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public int getPayFee() {
		return payFee;
	}

	public void setPayFee(int payFee) {
		this.payFee = payFee;
	}

	@Override
	public String toString() {
		return String.format("     %s \t%s\t\t%s  \t%,d원  \n", carNo, iptTime, optTime, payFee);
	}

}
