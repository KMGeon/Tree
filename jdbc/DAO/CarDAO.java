package DAO;

import java.sql.*;
import java.util.*;

import DBUtil.*;
import VO.*;
import View.*;

public class CarDAO {
	private CarDAO() {
	}

	private static CarDAO instance = new CarDAO();

	public static CarDAO getInstance() {
		return instance;
	}

	public int insertCar(CarVO vo) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO CAR( ");
		builder.append("            CAR_NO ");
		builder.append("          , MEM_ID) ");
		builder.append("VALUES( ");
		builder.append("      ? ");
		builder.append("     , NULL) ");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCarNo());
		int row = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return row;
	}

	public CarVO selectOne(String searchId) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT CAR_NO ");
		builder.append(" FROM   PARKING ");
		builder.append(" WHERE  CAR_NO = '" + searchId + "'");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		CarVO vo = null;
		if (rs.next()) {
			String carNo = rs.getString("CAR_NO");
			vo = new CarVO(carNo);
		}
		rs.close();
		stmt.close();
		conn.close();
		return vo;
	}

	public int deleteCar(String deleteCar) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM CAR ");
		builder.append("WHERE CAR_NO = ?");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, deleteCar);
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public CarVO DuplicateCarNo(String searchcarno) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT CAR_NO ");
		builder.append(" FROM CAR ");
		builder.append(" WHERE CAR_NO = '" + searchcarno + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		CarVO vo = null;
		if (resultSet.next()) { // 중복검사통과
			String carNo = resultSet.getString("car_no");
			vo = new CarVO();
			vo.setCarNo(carNo);

		}
		resultSet.close();
		statement.close();
		conn.close();
		return vo;
	}

	public int membershipCarNo(String carNo) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO car ( ");
		builder.append("     car_no, ");
		builder.append("     mem_id ");
		builder.append(" ) VALUES ( ");
		builder.append("     ?, ");
		builder.append("     ? ");
		builder.append(" )");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, carNo);
		statement.setString(2, LoginView.membershipId);
		// insert, update, delete가 모두 executeUpdate()메소드를 호출
		try {
			int executeUpdate = statement.executeUpdate();
			return executeUpdate;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			statement.close();
			conn.close();
		}
	}

	public CarVO mypageCarNo() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT CAR_NO ");
		builder.append(" FROM   CAR ");
		builder.append(" WHERE  MEM_ID = '" + LoginView.memId + "'");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		CarVO vo1 = null;
		if (rs.next()) {
			String carNo = rs.getString("CAR_NO");
			vo1 = new CarVO(carNo);
		}
		rs.close();
		stmt.close();
		conn.close();
		return vo1;
	}

	public CarVO mypageListCarNo() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT CAR_NO ");
		builder.append(" FROM   CAR ");
		builder.append(" WHERE  MEM_ID = '" + LoginView.memId + "'");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		CarVO vo1 = null;
		if (rs.next()) {
			String carNo = rs.getString("CAR_NO");
			vo1 = new CarVO(carNo);
		}
		rs.close();
		stmt.close();
		conn.close();
		return vo1;
	}

	public int mypageCarNo(String no, String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE car ");
		builder.append("     SET ");
		builder.append("         car_no = ? ");
		builder.append(" WHERE ");
		builder.append("     mem_id = ? ");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, no);
		statement.setString(2, id);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public List<CarVO> adminSearchList(String searchId) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		// 1. + 기호로 문자열 결합
		// 2. StringBuilder(StringBuffer): 속도가 빠르다
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		builder.append("     car_no");
		builder.append(" FROM");
		builder.append("     car");
		builder.append(" WHERE");
		builder.append("     mem_id ='" + searchId + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 ~ 무한대 -> flux
		List<CarVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String carNo = resultSet.getString("car_no");
			list.add(new CarVO(carNo));
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}
	public List<CarVO> adminCarList(String searchId) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		// 1. + 기호로 문자열 결합
		// 2. StringBuilder(StringBuffer): 속도가 빠르다
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		builder.append("     car_no");
		builder.append(" FROM");
		builder.append("     car");
		builder.append(" WHERE");
		builder.append("     mem_id ='" + searchId + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 ~ 무한대 -> flux
		List<CarVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String carNo = resultSet.getString("car_no");
			list.add(new CarVO(carNo));
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}

	public int mypageCarNoDelete(String carno) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" DELETE FROM");
		builder.append(" car ");
		builder.append(" WHERE");
		builder.append("     mem_id = '" + carno + "'");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		// insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

}
