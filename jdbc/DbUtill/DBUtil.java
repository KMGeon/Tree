package DBUtil;

import java.sql.*;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		String myDriver = "oracle.jdbc.driver.OracleDriver";
		String myURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String myID = "aven";
		String myPW = "java";
		try {
			Class.forName(myDriver);
			conn = DriverManager.getConnection(myURL, myID, myPW);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
