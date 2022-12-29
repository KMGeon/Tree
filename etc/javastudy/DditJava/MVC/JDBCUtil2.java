package JavaMVCTest.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/*
    db.properties파일의 내용으로 db정보를 설정하는 방법
    1.properties    객체 이용하기

 */
public class JDBCUtil2 {
    static Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("res/db.properties"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 로딩 완료!");
        } catch (ClassNotFoundException ex) {
            System.out.println("드라이버 로딩 실패!");
        } catch (IOException ex) {
            System.out.println("해당 파일이 없거나 입출력 오류입니다.");

        }
    }

    /*
    커넥션 객체 생성하기
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "charon", "java");
        } catch (SQLException e) {
            System.out.println("DB연결 실패");
            e.printStackTrace();
            return null;
        }

    }

    //자원반납
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
