package chapter20;

import java.sql.Date;
import java.util.Objects;

// VO(Value Object) 현업에서는 이렇게 쓰지만 뜻이 약간 다름
// DTO(Data Transfer Object)
public class MemoVO {
	// 필드
	private int id;
	private	String title;
	private String contents;
	private Date registerDate;
	private Date modifyDate;
	//생성자
	public MemoVO() {
		
		
	}
	
	public MemoVO(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	
	public MemoVO(int id, String title, String contents) {
		this.id = id;
		this.title = title;
		this.contents = contents;
	}

	public MemoVO(int id, String title, String contents, Date registerDate, Date modifyDate) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.registerDate = registerDate;
		this.modifyDate = modifyDate;
	}
	// 메소드
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return String.format("%s \t %s \t %s \t %s \t %s \t \n", id, title, contents, registerDate, modifyDate);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MemoVO)) {
			return false;
		}
		MemoVO other = (MemoVO) obj;
		return id == other.id;
	}


	
}
