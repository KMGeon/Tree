package kr.or.funding.rvCnm;

import java.sql.Date;

//리뷰댓글
public class RvCnmVO {
	private String rvCnt;// 리뷰내용
	private int mbsNm;// 회원번호
	private int rvNum;// 리뷰번호
	private Date wtDt;// 일자
	private int cmmNum;// 댓글번호

	public RvCnmVO(String rvCnt, int mbsNm, int rvNum, Date wtDt, int cmmNum) {

		this.rvCnt = rvCnt;
		this.mbsNm = mbsNm;
		this.rvNum = rvNum;
		this.wtDt = wtDt;
		this.cmmNum = cmmNum;
	}

	public String getRvCnt() {
		return rvCnt;
	}

	public void setRvCnt(String rvCnt) {
		this.rvCnt = rvCnt;
	}

	public int getMbsNm() {
		return mbsNm;
	}

	public void setMbsNm(int mbsNm) {
		this.mbsNm = mbsNm;
	}

	public int getRvNum() {
		return rvNum;
	}

	public void setRvNum(int rvNum) {
		this.rvNum = rvNum;
	}

	public Date getWtDt() {
		return wtDt;
	}

	public void setWtDt(Date wtDt) {
		this.wtDt = wtDt;
	}

	public int getCmmNum() {
		return cmmNum;
	}

	public void setCmmNum(int cmmNum) {
		this.cmmNum = cmmNum;
	}

}
