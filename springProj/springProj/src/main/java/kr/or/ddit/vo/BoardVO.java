package kr.or.ddit.vo;

public class BoardVO {
	
	private String memId; 
	private String memPass;
	private String memName;
	private String memJob; 
	private String memLike; 

	public BoardVO() {}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
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

	@Override
	public String toString() {
		return "BoardVO [memId=" + memId + ", memPass=" + memPass + ", memName=" + memName + ", memJob=" + memJob
				+ ", memLike=" + memLike + "]";
	}	
}
