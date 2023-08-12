package kr.or.funding.pyHis.vo;

import java.sql.Date;

//결제내역
public class PyHisVO {
	private int pyNum;// 결제번호
	private int ptNum;// 상품번호
	private int mbsNum;// 회원번호
	private int pyAm;// 결제금액
	private int commi;// 수수료
	private int pyQty;// 수량
	private Date pyDt;// 일자
	private String ptNm;
	

	public String getPtNm() {
		return ptNm;
	}

	public void setPtNm(String ptNm) {
		this.ptNm = ptNm;
	}

	public int getPyNum() {
		return pyNum;
	}

	public void setPyNum(int pyNum) {
		this.pyNum = pyNum;
	}

	public int getPtNum() {
		return ptNum;
	}

	public void setPtNum(int ptNum) {
		this.ptNum = ptNum;
	}

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

	public int getPyAm() {
		return pyAm;
	}

	public void setPyAm(int pyAm) {
		this.pyAm = pyAm;
	}

	public int getCommi() {
		return commi;
	}

	public void setCommi(int commi) {
		this.commi = commi;
	}

	public int getPyQty() {
		return pyQty;
	}

	public void setPyQty(int pyQty) {
		this.pyQty = pyQty;
	}

	public Date getPyDt() {
		return pyDt;
	}

	public void setPyDt(Date pyDt) {
		this.pyDt = pyDt;
	}

}