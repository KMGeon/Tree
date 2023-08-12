package JDBCBoardDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import JDBCBoardVO.BoardVO;
import Util.MyBatisUtil;

public class BoardDAOImpl implements IBoardDAO{
	
	private static IBoardDAO boardDao;
	
	private SqlSession sqlSession;
	
	private BoardDAOImpl() {
		sqlSession = MyBatisUtil.getInstance(true);
	}
	
	public static IBoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDAOImpl();
		}
		return boardDao;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		
		List<BoardVO> boardList = sqlSession.selectList("board.boardAllList");
		
		return boardList;
	}

	@Override
	public int insertWrite(BoardVO vo) {
		
		int cnt = sqlSession.insert("board.insertBoard", vo);
		
		return cnt;
	}

	@Override
	public int updateWrite(BoardVO vo) {
		
		int cnt = sqlSession.update("board.updateBoard", vo);
				
		return cnt;
	}

	@Override
	public int deleteWrite(int boardNo) {
		
		int cnt = sqlSession.delete("board.deleteboard", boardNo);
		
		return cnt;
	}

	@Override
	public List<BoardVO> searchWrite(BoardVO vo) {
		
		List<BoardVO> boardList = sqlSession.selectList("board.searchBoardList", vo);
				
		return boardList;
	}
	

}
