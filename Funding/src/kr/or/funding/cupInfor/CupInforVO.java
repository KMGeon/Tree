package kr.or.funding.cupInfor;

import java.sql.Date;

//쿠폰정보
public class CupInforVO {
	private int cupNuml;// 쿠폰번호
	private String dicIf;// 할인정보
	private Date rpDt;// 사용기간
	private int mbsNum;// 회원번호
	private String cupNm;// 쿠폰이름

	public int getCupNuml() {
		return cupNuml;
	}

	public String getDicIf() {
		return dicIf;
	}

	public void setDicIf(String dicIf) {
		this.dicIf = dicIf;
	}

	public Date getRpDt() {
		return rpDt;
	}

	public void setRpDt(Date rpDt) {
		this.rpDt = rpDt;
	}

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

	public String getCupNm() {
		return cupNm;
	}

	public void setCupNm(String cupNm) {
		this.cupNm = cupNm;
	}

}