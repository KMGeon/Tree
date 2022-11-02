package kr.or.ddit.vo;

public class MemVO {
	private String memId;
	private String memName;
	private String memJob;
	private String memLike;
	private String memHp;
	
	public MemVO() {}

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

	public String getMemJob() {
		return memJob;
	}

	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}

	public String getMemLike() {
		return memLike;
	}

	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

	@Override
	public String toString() {
		return "MemVO [memId=" + memId + ", memName=" + memName + ", memJob=" + memJob + ", memLike=" + memLike
				+ ", memHp=" + memHp + "]";
	}
	
}
