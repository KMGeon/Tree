package kr.or.funding.oneIq;

import java.util.Date;

public class OneiqVO {// 1:1문의
	private int iqNum;// 문의번호
	private Date wtDt;// 작성일자
	private String iqCnt;// 내용
	private String iqTit;// 문의제목
	private String iqCgy;// 카테고리
	private String cmmCnt;// 답글내용
	private Date cmmDt;// 답글일자
	private String cmmr;// 답글작성자
	private int mbsNum;// 회원번호

	public int getIqNum() {
		return iqNum;
	}

	public void setIqNum(int iqNum) {
		this.iqNum = iqNum;
	}

	public Date getWtDt() {
		return wtDt;
	}

	public void setWtDt(Date wtDt) {
		this.wtDt = wtDt;
	}

	public String getIqCnt() {
		return iqCnt;
	}

	public void setIqCnt(String iqCnt) {
		this.iqCnt = iqCnt;
	}

	public String getIqTit() {
		return iqTit;
	}

	public void setIqTit(String iqTit) {
		this.iqTit = iqTit;
	}

	public String getIqCgy() {
		return iqCgy;
	}

	public void setIqCgy(String iqCgy) {
		this.iqCgy = iqCgy;
	}

	public String getCmmCnt() {
		return cmmCnt;
	}

	public void setCmmCnt(String cmmCnt) {
		this.cmmCnt = cmmCnt;
	}

	public Date getCmmDt() {
		return cmmDt;
	}

	public void setCmmDt(Date cmmDt) {
		this.cmmDt = cmmDt;
	}

	public String getCmmr() {
		return cmmr;
	}

	public void setCmmr(String cmmr) {
		this.cmmr = cmmr;
	}

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

}