package kr.or.funding.wishlist.vo;

//찜목록
public class WishlistVO {
	
	// 찜목록 테이블 부분
	private int ptNum;// 상품번호
	private int mbsNum;// 회원번호
	
	// 상품정보 부분
	private String ptNm; // 상품이름  ---
	private String itdt;// 소개내용   --
	private int ptPrc;// 가격  --
	private String stDt;// 시작일  --
	private String edDt;// 종료일  --
	private String ptCgy;// 수량   --
	private int pyQty;// 카테고리   --
	
	// 회원 부분
	private int mbsAhy;// 권한(0:관리자 1:일반회원 2:판매자) --
	
	private long atchFileId = -1;// 첨부파일번호
	
	private String mbsId; // 아이디
	private String mbsPw; // 비밀번호
	private String mbsMail;// 이메일
	private String mbsAddr;// 주소
	private String brDt;// 생년월일
	private String mbsPh;// 핸드폰번호
	private String rfCd;// 추천인코드
	private String mbsNm;// 이름
	private int milgNum;// 마일리지번호 
	private String useAt = "N";// 로그인 조건

	
	
	
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

	@Override
	public String toString() {
		return "WishlistVO [ptNum=" + ptNum + ", mbsNum=" + mbsNum + "]";
	}

	
	
	////================================
	
	
	public String getPtNm() {
		return ptNm;
	}

	public void setPtNm(String ptNm) {
		this.ptNm = ptNm;
	}

	public String getItdt() {
		return itdt;
	}

	public void setItdt(String itdt) {
		this.itdt = itdt;
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

	public int getMbsAhy() {
		return mbsAhy;
	}

	public void setMbsAhy(int mbsAhy) {
		this.mbsAhy = mbsAhy;
	}

	public long getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
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

	public String getMbsNm() {
		return mbsNm;
	}

	public void setMbsNm(String mbsNm) {
		this.mbsNm = mbsNm;
	}

	public int getMilgNum() {
		return milgNum;
	}

	public void setMilgNum(int milgNum) {
		this.milgNum = milgNum;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}


	////// ===============
	
	

}
