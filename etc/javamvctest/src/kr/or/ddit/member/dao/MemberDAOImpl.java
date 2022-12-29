package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDAOImpl implements IMemberDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insetMember(MemberVO mv) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO mymember(mem_id, mem_name, mem_tel, mem_addr, reg_dt)"
					+ "VALUES (?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원등록 과정중 예외발생!", e);
		} finally {
			// 자원반납
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();
			String sql = " update  mymember" + " set     mem_name = ?," + "         mem_tel = ?,"
					+ "         mem_addr = ?" + " where   mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 수정중 오류발생!", e);
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = " delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 삭제중 오류발생!", e);
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select * from mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setMemId(rs.getString("mem_id"));
				mv.setMemName(rs.getString("mem_name"));
				mv.setMemTel(rs.getString("mem_tel"));
				mv.setMemAddr(rs.getString("mem_addr"));
				memList.add(mv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("전체회원 조회 중 오류발생!", e);
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}


	@Override
	public boolean checkMember(String memId) {

		boolean chk = false;
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select  count(*) as cnt " + 
					  	 " from    mymember" + 
					 	 " where   mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 확인중 오류발생!", e);
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

	@Override
	public List<MemberVO> searchMemberList(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " select * from mymember where 1=1 ";
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO mv2 = new MemberVO();
				mv2.setMemId(rs.getString("mem_id"));
				mv2.setMemName(rs.getString("mem_name"));
				mv2.setMemTel(rs.getString("mem_tel"));
				mv2.setMemAddr(rs.getString("mem_addr"));
				memList.add(mv2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

	
}
