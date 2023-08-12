package kr.or.funding.board2.vo;

import java.sql.Date;

public class Notice2VO {
    private String ntNum;
	private String ntClf;
	private String ntTit;
	private String ntCnt;
	private Date ntWriteDt;
	private long atchFileId=-1;
	
	
	public String getNtNum() {
		return ntNum;
	}
	public void setNtNum(String ntNum) {
		this.ntNum = ntNum;
	}
	public String getNtClf() {
		return ntClf;
	}
	public void setNtClf(String ntClf) {
		this.ntClf = ntClf;
	}
	public String getNtTit() {
		return ntTit;
	}
	public void setNtTit(String ntTit) {
		this.ntTit = ntTit;
	}
	public String getNtCnt() {
		return ntCnt;
	}
	public void setNtCnt(String ntCnt) {
		this.ntCnt = ntCnt;
	}
	public Date getNtWriteDt() {
		return ntWriteDt;
	}
	public void setNtWriteDt(Date ntWriteDt) {
		this.ntWriteDt = ntWriteDt;
	}
	public long getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}
	
	
}
