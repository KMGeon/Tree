package kr.or.ddit.comm.vo;

import java.util.Date;

public class AtchFileVO {

	private long atchFileId = -1; 	// 첨부파일ID
	private Date createDt; 			// 생성일자
	private int fileSn = 1; 		// 파일순번
	private String fileStreCours; 	// 파일저장경로
	private String streFileNm;		// 저장파일명
	private String orignlFileNm;	// 원본파일명
	private String fileExtsn;		// 파일확장자
	private String fileCn = "";			// 파일내용
	private long fileSize = 0;		// 파일크기

	public long getAtchFileId() {
		return atchFileId;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public int getFileSn() {
		return fileSn;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	

}
