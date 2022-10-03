package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	static {
		try {
			/*
			 * Class<?> clazz = Class.forName("oracle.jdbc.driver.OracleDriver"); // 리턴값을 받는방법(생략 가능)
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패ㅜㅜㅜ");
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc14", "java");
		} catch (Exception e) {
			System.out.println("DB 연결 실패ㅜㅜ");
			e.printStackTrace();
			return null;
		}
	}

	// 자원반납하는 메서드
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}
}
