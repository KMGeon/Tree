package JDBCBoardService;

import java.util.List;

import JDBCBoardDao.BoardDAOImpl;
import JDBCBoardDao.IBoardDAO;
import JDBCBoardVO.BoardVO;

public class BoardServiceImpl implements IBoardService{
	
	private static IBoardService boardService;
	
	private IBoardDAO boardDao;
	
	private BoardServiceImpl() {
		boardDao = BoardDAOImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		List<BoardVO> boardList = boardDao.displayBoardAll();
		return boardList;
	}

	@Override
	public int insertWrite(BoardVO vo) {
		int cnt = boardDao.insertWrite(vo);
		return cnt;
	}

	@Override
	public int updateWrite(BoardVO vo) {
		int cnt = boardDao.updateWrite(vo);
		return cnt;
	}

	@Override
	public int deleteWrite(int boardNo) {
		int cnt = boardDao.deleteWrite(boardNo);
		return cnt;
	}

	@Override
	public List<BoardVO> searchWrite(BoardVO vo) {
		List<BoardVO> searchList = boardDao.searchWrite(vo);
		return searchList;
	}
	
	
}
