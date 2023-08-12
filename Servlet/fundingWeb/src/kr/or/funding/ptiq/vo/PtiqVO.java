package kr.or.funding.ptiq.vo;

import java.sql.Date;

//상품문의
public class PtiqVO {
	private int ptIqNum;// 상품문의번호
	private String iqTit;// 문의제목
	private String iqCnt;// 문의내용
	private Date wtDt;// 작성일자
	private int mbsNum;// 회원번호
	private String cgy;// 카테고리
	private String cmmCnt;// 답글내용
	private Date cmmDt;// 답글일자
	private String cmmr;// 답글작성자
	private int ptNum;// 상품번호

	public int getPtIqNum() {
		return ptIqNum;
	}

	public void setPtIqNum(int ptIqNum) {
		this.ptIqNum = ptIqNum;
	}

	public String getIqTit() {
		return iqTit;
	}

	public void setIqTit(String iqTit) {
		this.iqTit = iqTit;
	}

	public String getIqCnt() {
		return iqCnt;
	}

	public void setIqCnt(String iqCnt) {
		this.iqCnt = iqCnt;
	}

	public Date getWtDt() {
		return wtDt;
	}

	public void setWtDt(Date wtDt) {
		this.wtDt = wtDt;
	}

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

	public String getCgy() {
		return cgy;
	}

	public void setCgy(String cgy) {
		this.cgy = cgy;
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

	public int getPtNum() {
		return ptNum;
	}

	public void setPtNum(int ptNum) {
		this.ptNum = ptNum;
	}

}