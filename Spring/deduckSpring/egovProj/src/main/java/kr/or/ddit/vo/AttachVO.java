package kr.or.ddit.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;
//ATTACH 테이블 ValueObject
public class AttachVO {
	private String userNo;
	private int seq;
	private String filename;
	private int filesize;
	private Date regdate;
	private String tid;
	private String attachName;
	private int attachSize;
	private String attachType;
	
	public AttachVO() {}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public int getAttachSize() {
		return attachSize;
	}

	public void setAttachSize(int attachSize) {
		this.attachSize = attachSize;
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	@Override
	public String toString() {
		return "AttachVO [userNo=" + userNo + ", seq=" + seq + ", filename=" + filename + ", filesize=" + filesize
				+ ", regdate=" + regdate + ", tid=" + tid + ", attachName=" + attachName + ", attachSize=" + attachSize
				+ ", attachType=" + attachType + "]";
	}
	
	
	
}
