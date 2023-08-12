package JDBCBoardDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCBoardVO.BoardVO;
import Util.JDBCUtil;

public class BoardDAOImpl implements IBoardDAO{
	
	private static IBoardDAO boardDao;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	private BoardDAOImpl() {
		
	}
	
	public static IBoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDAOImpl();
		}
		return boardDao;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " select * from jdbc_board ";
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("BOARD_NO"));
				bv.setBoardTitle(rs.getString("BOARD_TITLE"));
				bv.setBoardWriter(rs.getString("BOARD_WRITER"));
				bv.setBoardDate(rs.getDate("BOARD_DATE"));
				bv.setBoardContent(rs.getString("BOARD_CONTENT"));
				
				boardList.add(bv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("게시판 글 전체 검색 중 오류 발생!",e);
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}

	@Override
	public int insertWrite(BoardVO vo) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " insert into jdbc_board(board_no "
					+ ", board_title "
					+ ", board_writer "
					+ ", board_date "
					+ ", board_content ) "
					+ " values(board_seq.nextVal "
					+ ", ? "
					+ ", ? "
					+ ", sysdate "
					+ ", ? ) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoardTitle());
			pstmt.setString(2, vo.getBoardWriter());
			pstmt.setString(3, vo.getBoardContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("게시판 글 작성 중 오류발생", e);
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		
		return cnt;
	}

	@Override
	public int updateWrite(BoardVO vo) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " update jdbc_board "
					+ " set   board_title = ? "
					+ "    , board_writer = ? "
					+ "    , board_content = ? "
					+ " where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoardTitle());
			pstmt.setString(2, vo.getBoardWriter());
			pstmt.setString(3, vo.getBoardContent());
			pstmt.setInt(4, vo.getBoardNo());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("게시판 글 수정 중 오류발생",e);
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteWrite(int boardNo) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " delete from jdbc_board "
					+ " where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("게시판 글 삭제 중 오류발생",e);
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> searchWrite(BoardVO vo) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " select * from jdbc_board where 1 = 1 ";
			
			if(vo.getBoardNo() != 0) {
				sql += " and board_no = ? ";
			}
			
			if(vo.getBoardTitle() != null && !(vo.getBoardTitle().equals(""))) {
				sql += " and board_title = ? ";
			}
			
			if(vo.getBoardWriter() != null && !(vo.getBoardWriter().equals(""))) {
				sql += " and board_writer = ? ";
			}
			
			if(vo.getBoardDate() != null && !(vo.getBoardDate().equals(""))) {
				sql += " and board_date = sysdate ";
			}
			
			if(vo.getBoardContent() != null && !(vo.getBoardContent().equals(""))) {
				sql += " and board_content like '%' || ? || '%'";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(vo.getBoardNo() != 0) {
				pstmt.setInt(index++, vo.getBoardNo());
			}
			
			if(vo.getBoardTitle() != null && !(vo.getBoardTitle().equals(""))) {
				pstmt.setString(index++, vo.getBoardTitle());
			}
			
			if(vo.getBoardWriter() != null && !(vo.getBoardWriter().equals(""))) {
				pstmt.setString(index++, vo.getBoardWriter());
			}
			
			if(vo.getBoardDate() != null && !(vo.getBoardDate().equals(""))) {
				pstmt.setDate(index++, (Date)vo.getBoardDate());
			}
			
			if(vo.getBoardContent() != null && !(vo.getBoardContent().equals(""))) {
				pstmt.setString(index++, vo.getBoardContent());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo2 = new BoardVO();
				vo2.setBoardNo(rs.getInt("board_no"));
				vo2.setBoardTitle(rs.getString("board_title"));
				vo2.setBoardWriter(rs.getString("board_writer"));
				vo2.setBoardDate(rs.getDate("board_date"));
				vo2.setBoardContent(rs.getString("board_content"));
				
				boardList.add(vo2);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("게시판 검색 중 오류 발생!",e);
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}
	

}
