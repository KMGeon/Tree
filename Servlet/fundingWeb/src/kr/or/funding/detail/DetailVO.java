package kr.or.funding.detail;

import java.sql.Date;

public class DetailVO {
	private int ptNum;// 상품번호
	private String ptNm;// 회원버호
	private int mbsNum;// 상품이름
	private String itdt;// 소개내용
	private int imNum;// 이미지번호
	private int ptPrc;// 가격
	private String stDt;// 시작일
	private String edDt;// 종료일
	private String ptCgy;// 수량
	private int pyQty;// 카테고리
	private long atchFileId = -1;// 첨부파일번호
	
	private Date creatDt; 			// 생성일자
	private int fileSn = 1; 			// 파일순번
	private String fileStreCours; 	// 파일저장경로
	private String streFileNm; 		// 저장파일이름
	private String orignlFileNm; 	// 원본파일명
	private String fileExtsn; 		// 파일확장자
	private String fileCn = ""; 			// 파일내용
	private long fileSize = 0; 		// 파일크기
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
	public String getStDt() {
		return stDt;
	}
	public void setStDt(String stDt) {
		this.stDt = stDt;
	}
	public String getEdDt() {
		return edDt;
	}
	public void setEdDt(String edDt) {
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
	public long getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}
	public Date getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(Date creatDt) {
		this.creatDt = creatDt;
	}
	public int getFileSn() {
		return fileSn;
	}
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}
