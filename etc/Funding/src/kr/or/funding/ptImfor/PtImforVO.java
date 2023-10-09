package kr.or.funding.ptImfor;

import java.sql.Date;

//상품정보
public class PtImforVO {
	private int ptNum;// 상품번호
	private String ptNm;// 회원버호
	private int mbsNum;// 상품이름
	private String itdt;// 소개내용
	private int imNum;// 이미지번호
	private int ptPrc;// 가격
	private Date stDt;// 시작일
	private Date edDt;// 종료일
	private String ptCgy;// 수량
	private int pyQty;// 카테고리

	public int getPtNum() {
		return ptNum;
	}

	public void setPtNum(int ptNum) {
		this.ptNum = ptNum;
	}

	public String getPtNm() {
		return ptNm;
	}

	public void setPtNm(String ptNm) {
		this.ptNm = ptNm;
	}

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

	public String getItdt() {
		return itdt;
	}

	public void setItdt(String itdt) {
		this.itdt = itdt;
	}

	public int getImNum() {
		return imNum;
	}

	public void setImNum(int imNum) {
		this.imNum = imNum;
	}

	public int getPtPrc() {
		return ptPrc;
	}

	public void setPtPrc(int ptPrc) {
		this.ptPrc = ptPrc;
	}

	public Date getStDt() {
		return stDt;
	}

	public void setStDt(Date stDt) {
		this.stDt = stDt;
	}

	public Date getEdDt() {
		return edDt;
	}

	public void setEdDt(Date edDt) {
		this.edDt = edDt;
	}

	public String getPtCgy() {
		return ptCgy;
	}

	public void setPtCgy(String ptCgy) {
		this.ptCgy = ptCgy;
	}

	public int getPyQty() {
		return pyQty;
	}

	public void setPyQty(int pyQty) {
		this.pyQty = pyQty;
	}

}