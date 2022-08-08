package JavaMVCTest.MemberDAO;

import HighJava.src.JDBC.Util.JDBCUtil2;
import JavaMVCTest.MemberVO.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberDAOImpl implements IMemberDAO {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public int insertMember(MemberVO mv) {
        int cnt = 0;
        try {
            conn = JDBCUtil2.getConnection();
            String sql = " INSERT INTO mymember( mem_id, mem_name,mem_tel,mem_addr, REG_DT)"
                    + " VALUES(?,?,?,?,sysdate)";
            pstmt = Objects.requireNonNull(conn).prepareStatement(sql);
            pstmt.setString(1, mv.getMemID());
            pstmt.setString(2, mv.getMemName());
            pstmt.setString(3, mv.getMemTel());
            pstmt.setString(4, mv.getMemAddr());

            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("회원등록 과정중 예외발생 ~!", e);
        } finally {
            JDBCUtil2.close(conn, stmt, pstmt, rs);
        }
        return cnt;
    }

    @Override
    public boolean checkMember(String memId) {
        boolean chk = false;
        int cnt = 0;
        try {
            conn = JDBCUtil2.getConnection();
            String sql = " select count(*) as cnt from mymember "
                    + " where mem_id=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            cnt = 0;
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            if (cnt > 0) {
                chk = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("회원체크 과정중 예외발생 ~!", e);
        } finally {
            JDBCUtil2.close(conn, stmt, pstmt, rs);
        }
        return chk;
    }

    @Override
    public int updateMember(MemberVO mv) {
        int cnt = 0;
        try {
            conn = JDBCUtil2.getConnection();
            String sql = " UPDATE mymember " +
                    " SET mem_name = ? " +
                    "    , mem_tel = ? " +
                    "   , mem_addr = ? " +
                    "  where mem_id =?  ";

            pstmt = Objects.requireNonNull(conn).prepareStatement(sql);
            pstmt.setString(1, mv.getMemName());
            pstmt.setString(2, mv.getMemTel());
            pstmt.setString(3, mv.getMemAddr());
            pstmt.setString(4, mv.getMemID());

            cnt = pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("회원업데이트 과정중 예외발생 ~!", e);

        } finally {
            JDBCUtil2.close(conn, stmt, pstmt, rs);
        }
        return cnt;
    }

    @Override
    public int deleteMember(MemberVO mv) {
        int cnt = 0;
        try {
            conn = JDBCUtil2.getConnection();
            String sql = " delete from mymember where mem_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mv.getMemID());

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("회원삭제 과정중 예외발생 ~!", e);
        } finally {
            JDBCUtil2.close(conn, stmt, pstmt, rs);
        }
        return cnt;
    }

    @Override
    public List<MemberVO> getAllmemberList() {
        List<MemberVO> list = new ArrayList<MemberVO>();
        try {
            conn = JDBCUtil2.getConnection();
            String sql = " select * from mymember ";
            stmt = Objects.requireNonNull(conn).createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MemberVO VO = new MemberVO();
                VO.setMemID(rs.getString(("mem_id")));
                VO.setMemName(rs.getString(("mem_name")));
                VO.setMemTel(rs.getString(("mem_tel")));
                VO.setMemAddr(rs.getString(("mem_addr")));
                list.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("전체회원 조회 중 예외발생 ~!", e);
        } finally {
            JDBCUtil2.close(conn, stmt, pstmt, rs);
        }
        return list;
    }

}
