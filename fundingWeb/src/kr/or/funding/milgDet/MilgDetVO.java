package kr.or.funding.milgDet;

import java.sql.Date;

//마일리지
public class MilgDetVO {
	private int mbsNum;// 회원번호
	private int milgNum;// 마일리지번호
	private String milg;
	private Date milgDt;// 일자
	private String milgCgy;

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

	public int getMilgNum() {
		return milgNum;
	}

	public void setMilgNum(int milgNum) {
		this.milgNum = milgNum;
	}

	public String getMilg() {
		return milg;
	}

	public void setMilg(String milg) {
		this.milg = milg;
	}

	public Date getMilgDt() {
		return milgDt;
	}

	public void setMilgDt(Date milgDt) {
		this.milgDt = milgDt;
	}

	public String getMilgCgy() {
		return milgCgy;
	}

	public void setMilgCgy(String milgCgy) {
		this.milgCgy = milgCgy;
	}

}