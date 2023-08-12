package kr.or.ddit.member.vo;

import java.sql.Date;

/*
 *	DB테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스
 *
 * <p>
 *   DB테이블의 '컬럼'이 이클래스의 '멤버변수'가 된다.<br>
 *   DB테이블의 컬럼과 이 클래스의 멤버변수를 매핑하는 역할을 수행한다
 * </p>
 */
public class MemberVO {
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	private Date regDate;
	
	
	public MemberVO(String memId, String memName, String memTel, String memAddr, Date regDate) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
		this.memAddr = memAddr;
		this.regDate = regDate;
	}
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return String.format("MemberVO [memId=%s, memName=%s, memTel=%s, memAddr=%s, regDate=%s]", memId, memName,
				memTel, memAddr, regDate);
	}


}
