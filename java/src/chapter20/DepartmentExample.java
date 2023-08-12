package chapter20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class DepartmentExample {
	public static void main(String[] args) throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc17", "java");
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select dept_id, dept_name, dept_tel from department");
		
		while(resultSet.next()) {
			String deptId = resultSet.getString("dept_id");
			String deptName = resultSet.getString("dept_name");
			String deptTel = resultSet.getString("dept_tel");
			System.out.printf("%s\t%-10s\t%s\t\n", deptId, deptName, deptTel);
			
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		
		// 데이터를 처리하는 부분과 화면에 보여주는 부분을 분리해서 코드작성하기(Data=Model 과 View 부분)
		// 두 부분을 컨트롤(Controller)하는 세 가지를 MVC라고 한다.(MVC Pattern)
		
		
	}
}
