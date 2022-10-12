package JDBCBoardService;

import java.util.List;

import JDBCBoardVO.BoardVO;

public interface IBoardService {
	
	public List<BoardVO> displayBoardAll();
	
	public int insertWrite(BoardVO vo);
	
	public int updateWrite(BoardVO vo);
	
	public int deleteWrite(int boardNo);
	
	public List<BoardVO> searchWrite(BoardVO vo);

}
