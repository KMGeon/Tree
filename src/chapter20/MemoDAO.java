package chapter20;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;

//Data Access Object : 직접 데이터베이스에 접속해서 데이터 처리

public class MemoDAO {
	public List<MemoVO> selectList() throws Exception{
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc17", "java");
		Statement statement = connection.createStatement();
		// 문자열 결합
		// 1. + 기호로 문자열 결합
		// 2. StringBuiler(StringButter) : 속도가 빠르다.
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		builder.append("    id,");
		builder.append("    title,");
		builder.append("    contents,");
		builder.append("    register_date,");
		builder.append("    modify_date");
		builder.append(" FROM");
		builder.append("    memo");
		//Alt + shift + A (복붙 기술)
		String sql = builder.toString();
		
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과가: 0~무한대 -> flux 
		ArrayList<MemoVO> list = new ArrayList<>();
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			String contents = resultSet.getString("contents");
			Date registerDate = resultSet.getDate("register_date");
			Date modifyDate = resultSet.getDate("modify_date");
			list.add(new MemoVO(id, title, contents, registerDate, modifyDate));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	public MemoVO selectOne(int searchId) throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc17", "java");
		Statement statement = connection.createStatement();
		// 문자열 결합
		// 1. + 기호로 문자열 결합
		// 2. StringBuiler(StringButter) : 속도가 빠르다.
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		builder.append("    id,");
		builder.append("    title,");
		builder.append("    contents,");
		builder.append("    register_date,");
		builder.append("    modify_date");
		builder.append(" FROM");
		builder.append("    memo");
		builder.append(" WHERE");
		builder.append("    id =" + searchId);
		//builder.append(searchId); 위에것을  
		// Alt + shift + A (복붙 기술)
		String sql = builder.toString();
		
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과가 0 or 1 -> Mono
		MemoVO vo = null;
		if(resultSet.next()) {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			String contents = resultSet.getString("contents");
			Date registerDate = resultSet.getDate("register_date");
			Date modifyDate = resultSet.getDate("modify_date");
			vo = new MemoVO(id, title, contents, registerDate, modifyDate);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return vo;
	}
	public int insertMemo(MemoVO vo) throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc17", "java");
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO memo (");
		builder.append("    id,");
		builder.append("    title,");
		builder.append("    contents");
		builder.append(") VALUES (");
		builder.append("    memo_seq.nextval,");
		builder.append("    ?,");
		builder.append("    ?");
		builder.append(")");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getTitle()); 
		statement.setString(2, vo.getContents());
		//insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		
		return executeUpdate;
	}
	public int updateMemo(MemoVO vo) throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc17", "java");
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE memo");
		builder.append("    SET ");
		builder.append("        title = ?, ");
		builder.append("        contents = ?, ");
		builder.append("        modify_date = SYSDATE ");
		builder.append("WHERE ");
		builder.append("    id =? ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getTitle()); 
		statement.setString(2, vo.getContents());
		statement.setInt(3, vo.getId());
		//insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		
		return executeUpdate;
	}
	public int deleteMemo(int deleteId) throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc17", "java");
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append("    memo ");
		builder.append("WHERE ");
		builder.append("    id = ? ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, deleteId);
		//insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		
		return executeUpdate;
	}
	
	
	
}
