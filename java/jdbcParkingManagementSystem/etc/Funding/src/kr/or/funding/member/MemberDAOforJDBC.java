
package kr.or.funding.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.funding.util.JDBCUtil;

public class MemberDAOforJDBC implements IMemberDAO {

	private static IMemberDAO memDao;

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt; // Statement보다 PreparedStatement가 안정성이 높다.
	private ResultSet rs;

	private MemberDAOforJDBC() {

	}

	public static IMemberDAO getInstance() {

		if (memDao == null) {
			memDao = new MemberDAOforJDBC();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();

			String sql = "INSERT INTO MEMBER (mbs_num,mbs_id, MBS_PW, MBS_MAIL, mbs_addr, br_dt,mbs_ph,rf_cd,mbs_ahy,mbs_nm)"
					+ "VALUES(mbs_num_seq.nextval,?,?,?,?,?,?,?,1,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mv.getMbsNum());
			pstmt.setString(2, mv.getMbsId());
			pstmt.setString(3, mv.getMbsPw());
			pstmt.setString(4, mv.getMbsMail());
			pstmt.setString(5, mv.getMbsAddr());
			pstmt.setString(6, mv.getBrDt());
			pstmt.setString(7, mv.getMbsPh());
			pstmt.setString(8, mv.getRfCd());
			pstmt.setInt(9, mv.getMbsAhy());
			pstmt.setString(10, mv.getMbsNm());

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("회원등록 과정중 예외발생!", ex); // 실행시점예외 던지기(호출하는 메서드에서 의무적으로 try/catch로 감싸지 않아도됨)
		} finally {
			// 자원반납
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

//체크
	@Override
	public boolean checkMember(String memId) {
		boolean chk = false;

		try {
			conn = JDBCUtil.getConnection();

			String sql = "select count(*) as cnt from mymember " + " where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

	// 업데이트
	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();

			String sql = "UPDATE mymember " + " SET  mem_name = ?" + "    , mem_tel  = ? " + "    , mem_addr = ?"
					+ " WHERE mem_id   = ?";

			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, mv.getMemName());
//			pstmt.setString(2, mv.getMemTel());
//			pstmt.setString(3, mv.getMemAddr());
//			pstmt.setString(4, mv.getMemId());

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("회원수정 과정중 예외발생!", ex);
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	// 삭제

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "delete from mymember where mem_id =? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("회원삭제 과정중 예외발생!", ex);
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

//	전체 출력
	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			conn = JDBCUtil.getConnection();

			String sql = "select * from mymember";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setMbsNum(rs.getInt("mbsNum"));
				mv.setMbsId(rs.getString("mbsId"));
				mv.setMbsPw(rs.getString("mbsPw"));
				mv.setMbsMail(rs.getString("mbsMail"));
				mv.setMbsAddr(rs.getString("mbsAddr"));
				// mv.setbrDt(rs.getString("brDt"));
				mv.setMbsPh(rs.getString("mbsPh"));
				mv.setRfCd(rs.getString("rfCd"));
				mv.setMilgNum(rs.getInt("milgNum"));
				mv.setMbsAhy(rs.getInt("mbsAhy"));
				mv.setMbsNm(rs.getString("mbsNm"));
				mv.setFileNum(rs.getInt("fileNum"));

				memList.add(mv);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("전체 조회 중 예외발생!", ex);
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> searchMemberList(MemberVO mv) {
		// TODO Auto-generated method stub
		return null;
	}

}
		
	