package kr.or.funding.rv;

import java.sql.Date;

//리뷰
public class RvVO {
	private int rvNum;// 리뷰번호
	private int ptNum;// 상품번호
	private int mbsNum;// 회원번호
	private String cnt;// 내용
	private Date wtDt;// 작성일자
	private int fileNum;// 리뷰이미지번호

	public int getRvNum() {
		return rvNum;
	}

	public void setRvNum(int rvNum) {
		this.rvNum = rvNum;
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

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public Date getWtDt() {
		return wtDt;
	}

	public void setWtDt(Date wtDt) {
		this.wtDt = wtDt;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

}