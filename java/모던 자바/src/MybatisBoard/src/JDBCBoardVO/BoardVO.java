package JDBCBoardVO;

import java.util.Date;

public class BoardVO {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private Date boardDate;
	private String boardContent;
	
	public int getBoardNo() {
		return boardNo;
	}
	
	public String getBoardTitle() {
		return boardTitle;
	}
	
	public String getBoardWriter() {
		return boardWriter;
	}
	
	public Date getBoardDate() {
		return boardDate;
	}
	
	public String getBoardContent() {
		return boardContent;
	}
	
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardDate=" + boardDate + ", boardContent=" + boardContent + "]";
	}
	
	
}
