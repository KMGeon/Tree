package kr.or.funding.member.VO;

//회원
public class MemberVO {
	private int mbsNum; // 회원번호
	private String mbsId; // 아이디
	private String mbsPw; // 비밀번호
	private String mbsMail;// 이메일
	private String mbsAddr;// 주소
	private String brDt;// 생년월일
	private String mbsPh;// 핸드폰번호
	private String rfCd;// 추천인코드
	private int milgNum;// 마일리지번호
	private int mbsAhy;// 권한(0:관리자 1:일반회원 2:판매자)
	private String mbsNm;// 이름
	private long atchFileId = -1;// 첨부파일번호
	private String useAt = "N";

	public int getMbsNum() {
		return mbsNum;
	}

	public void setMbsNum(int mbsNum) {
		this.mbsNum = mbsNum;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getMbsId() {
		return mbsId;
	}

	public void setMbsId(String mbsId) {
		this.mbsId = mbsId;
	}

	public String getMbsPw() {
		return mbsPw;
	}

	public void setMbsPw(String mbsPw) {
		this.mbsPw = mbsPw;
	}

	public String getMbsMail() {
		return mbsMail;
	}

	public void setMbsMail(String mbsMail) {
		this.mbsMail = mbsMail;
	}

	public String getMbsAddr() {
		return mbsAddr;
	}

	public void setMbsAddr(String mbsAddr) {
		this.mbsAddr = mbsAddr;
	}

	public String getBrDt() {
		return brDt;
	}

	public void setBrDt(String brDt) {
		this.brDt = brDt;
	}

	public String getMbsPh() {
		return mbsPh;
	}

	public void setMbsPh(String mbsPh) {
		this.mbsPh = mbsPh;
	}

	public String getRfCd() {
		return rfCd;
	}

	public void setRfCd(String rfCd) {
		this.rfCd = rfCd;
	}

	public int getMilgNum() {
		return milgNum;
	}

	public void setMilgNum(int milgNum) {
		this.milgNum = milgNum;
	}

	public int getMbsAhy() {
		return mbsAhy;
	}

	public void setMbsAhy(int mbsAhy) {
		this.mbsAhy = mbsAhy;
	}

	public String getMbsNm() {
		return mbsNm;
	}

	public void setMbsNm(String mbsNm) {
		this.mbsNm = mbsNm;
	}

	public long getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}

}