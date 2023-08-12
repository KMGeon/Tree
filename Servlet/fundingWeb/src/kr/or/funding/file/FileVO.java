package kr.or.funding.file;

public class FileVO {
	private int fileNum;
	private int flOr;
	private String flEx;
	private String flPt;
	private String svFlNm;
	private int flSz;
	private int ntNum;
	private int rvNum;
	private int ptNum;
	private int mbsNum;
	public FileVO(int fileNum, int flOr, String flEx, String flPt, String svFlNm, int flSz, int ntNum, int rvNum,
			int ptNum, int mbsNum) {
		
		this.fileNum = fileNum;
		this.flOr = flOr;
		this.flEx = flEx;
		this.flPt = flPt;
		this.svFlNm = svFlNm;
		this.flSz = flSz;
		this.ntNum = ntNum;
		this.rvNum = rvNum;
		this.ptNum = ptNum;
		this.mbsNum = mbsNum;
	}
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public int getFlOr() {
		return flOr;
	}
	public void setFlOr(int flOr) {
		this.flOr = flOr;
	}
	public String getFlEx() {
		return flEx;
	}
	public void setFlEx(String flEx) {
		this.flEx = flEx;
	}
	public String getFlPt() {
		return flPt;
	}
	public void setFlPt(String flPt) {
		this.flPt = flPt;
	}
	public String getSvFlNm() {
		return svFlNm;
	}
	public void setSvFlNm(String svFlNm) {
		this.svFlNm = svFlNm;
	}
	public int getFlSz() {
		return flSz;
	}
	public void setFlSz(int flSz) {
		this.flSz = flSz;
	}
	public int getNtNum() {
		return ntNum;
	}
	public void setNtNum(int ntNum) {
		this.ntNum = ntNum;
	}
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
	
	
	
}
