package JavaMVCTest.Util;

import java.sql.*;
import java.util.ResourceBundle;

/*
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법2) ResourceBundle 객체 이용하기
 */
public class JDBCUtil3 {

    static ResourceBundle bundle; // Properties 객체변수 선언

    static {

        bundle = ResourceBundle.getBundle("db");

        try {

            Class.forName(bundle.getString("driver"));
            System.out.println("드라이버 로딩 완료");

        } catch (ClassNotFoundException ex) {
            System.out.println("드라이버 로딩 실패");
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(bundle.getString("url"), bundle.getString("username"), bundle.getString("password"));
        } catch (SQLException ex) {
            System.out.println("DB 연결 실패");
            ex.printStackTrace();
            return null;
        }
    }

    /*
     * 자원반납 메소드
     */
    public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
            }
        }

    }

}