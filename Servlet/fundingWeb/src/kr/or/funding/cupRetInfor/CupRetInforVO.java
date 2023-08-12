package kr.or.funding.cupRetInfor;

import java.sql.Date;

//쿠폰보유정보
public class CupRetInforVO {
	private int rtNum;// 보유번호
	private int cupNum;// 회원번호
	private int mbsNum;// 쿠폰번호
	private Date crtDt;// 생성일
	private int prDt;// 사용기간

	public int getRtNum() {
		return rtNum;
	}

	public void setRtNum(int rtNum) {
		this.rtNum = rtNum;
	}

	public int getCupNum() {
		return cupNum;
	}

	public void setCupNum(int cupNum) {
		this.cupNum = cupNum;
	}

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public int getPrDt() {
		return prDt;
	}

	public void setPrDt(int prDt) {
		this.prDt = prDt;
	}

}