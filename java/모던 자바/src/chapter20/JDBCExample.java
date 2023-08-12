package chapter20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class JDBCExample {
	public static void main(String[] args) throws Exception {
		//JDBC(Java DataBase Connectivity)
		
		/*0.준비물
		* JDBC Driver
		* Oracle JDBC => ojdbc.jar
		*1. 오라클 드라이버를 로딩
		*2. 접속 정보를 통해 접속
		*3. 쿼리를 전송
		*4. 쿼리 결과 받기
		*5. 리소스 닫기
		*
		*/
		new OracleDriver();
		
		// 1.드라이버 로딩 (에러는 트라이 캐치나 에드 쓰로우로 처리가능)
		
		// 1-1.reflection 기법을 사용한 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		// 1-2. DriverManager 클래스를 이용하여 드라이버 로딩
		//DriverManager.registerDriver(new OracleDriver());
		
		// 2. 접속 정보를 통해 데이터베이스에 접속
		// 127.0.0.1 == localhost
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pc17", "java");
		// 3. 쿼리를 작성(sqldeveloper의 워크시트에서 작성하는 것처럼)
		Statement statement = connection.createStatement();  
		// 4. 쿼리 전송 후 결과 받기
		ResultSet resultSet = statement.executeQuery("select mem_id, mem_name, mem_hp, mem_mail from member");
		// 5. 결과 분석(parsing)
		while (resultSet.next()) {
			String memId = resultSet.getString("mem_id");
			String memName = resultSet.getString("mem_name");
			String memHp = resultSet.getString("mem_hp");
			String memMail = resultSet.getString("mem_mail");
			System.out.printf("%s \t %s \t %s \t %s\n", memId,memName,memHp,memMail);
			
		}
		// 6. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();
		
	}
}
