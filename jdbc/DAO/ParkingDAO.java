package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import VO.CarVO;
import VO.ParkingVO;
import View.*;

public class ParkingDAO {
	private ParkingDAO() {
	}

	private static ParkingDAO instance = new ParkingDAO();

	public static ParkingDAO getInstance() {
		return instance;
	}

	public int insertPsNo(ParkingVO vo) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO PARKING( ");
		builder.append("PRK_NO");
		builder.append(" , PS_NO) ");
		builder.append("VALUES(SEQ_PARKING.NEXTVAL,?)");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getPsNo());
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public int insertPsNoM(ParkingVO vo) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO PARKING( ");
		builder.append("PRK_NO ");
		builder.append(" , CAR_NO ");
		builder.append(" , PS_NO ");
		builder.append(" , MEM_ID) ");
		builder.append("VALUES(SEQ_PARKING.NEXTVAL,?,?,'" + LoginView.memId + "')");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCarNo());
		pstmt.setString(2, vo.getPsNo());
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public int updatePrk(ParkingVO vo) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE PARKING ");
		builder.append("SET CAR_NO = ? ");
		builder.append("WHERE PS_NO = ?");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCarNo());
		pstmt.setString(2, vo.getPsNo());
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public int updateOptTime(CarVO vo) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE PARKING ");
		builder.append("SET    PRK_OPTTIME = SYSDATE ");
		builder.append("WHERE  CAR_NO = ?");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCarNo());
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public List<ParkingVO> optList(String searchCarNo) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT CAR_NO as CAR ");
		builder.append("     , TO_CHAR(PRK_IPTTIME, 'YYYY\"년\" MONDD\"일\" HH24:MI') as INTIME ");
		builder.append("     , TO_CHAR(SYSDATE, 'YYYY\"년\" MONDD\"일\" HH24:MI') as OUTTIME ");
		builder.append("     , TRUNC((SYSDATE - PRK_IPTTIME) * 24 * 60, 0) || '분 ' as TIME ");
		builder.append("     , TRUNC((SYSDATE - PRK_IPTTIME) * 24 * 60, 0) * 1000 FEE ");
		builder.append("FROM   PARKING ");
		builder.append("WHERE  CAR_NO = '" + searchCarNo + "'");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		List<ParkingVO> oList = new ArrayList<ParkingVO>();
		while (rs.next()) {
			String carNo = rs.getString("CAR");
			String inTime = rs.getString("INTIME");
			String outTime = rs.getString("OUTTIME");
			String time = rs.getString("TIME");
			int fee = rs.getInt("FEE");
			oList.add(new ParkingVO(carNo, inTime, outTime, time, fee));
		}
		rs.close();
		stmt.close();
		conn.close();
		return oList;
	}
	public List<ParkingVO> psaList(String searchPsNo) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT CAR_NO as CAR ");
		builder.append("     , TO_CHAR(PRK_IPTTIME, 'YYYY\"년\" MONDD\"일\" HH24:MI') as INTIME ");
		builder.append("     , TO_CHAR(SYSDATE, 'YYYY\"년\" MONDD\"일\" HH24:MI') as OUTTIME ");
		builder.append("     , TRUNC((SYSDATE - PRK_IPTTIME) * 24 * 60, 0) || '분 ' as TIME ");
		builder.append("     , TRUNC((SYSDATE - PRK_IPTTIME) * 24 * 60, 0) * 1000 FEE ");
		builder.append("FROM   PARKING ");
		builder.append("WHERE  PS_NO = '" + searchPsNo + "'");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		List<ParkingVO> oList = new ArrayList<ParkingVO>();
		while (rs.next()) {
			String carNo = rs.getString("CAR");
			String inTime = rs.getString("INTIME");
			String outTime = rs.getString("OUTTIME");
			String time = rs.getString("TIME");
			int fee = rs.getInt("FEE");
			oList.add(new ParkingVO(carNo, inTime, outTime, time, fee));
		}
		rs.close();
		stmt.close();
		conn.close();
		return oList;
	}

	public int updateOptNull(CarVO vo) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE PARKING ");
		builder.append("SET    PRK_OPTTIME = NULL ");
		builder.append("WHERE  CAR_NO = ?");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCarNo());
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public int deletePrk(String deletePrk) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM PARKING ");
		builder.append("WHERE CAR_NO = ?");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, deletePrk);
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public ParkingVO searchCarLoc(String searchCarNo) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  PS_NO ");
		builder.append(" FROM    PARKING ");
		builder.append(" WHERE   CAR_NO = '" + searchCarNo + "'");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		ParkingVO vo = null;
		if (rs.next()) {
			String psNo = rs.getString("PS_NO");
			vo = new ParkingVO(psNo);
		}
		rs.close();
		stmt.close();
		conn.close();
		return vo;
	}

	public int prkUpNull() throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE PARKING ");
		builder.append("SET    CAR_NO = NULL ");
		builder.append("WHERE  MEM_ID = '" + LoginView.memId + "'");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}
	public int prkUpCarNo(String carNo) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE PARKING ");
		builder.append("SET    CAR_NO = '" + carNo + "' ");
		builder.append("WHERE  MEM_ID = '" + LoginView.memId + "'");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}
	public ParkingVO searchTable(String searchId) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  CAR_NO ");
		builder.append(" FROM    PARKING ");
		builder.append(" WHERE   MEM_ID = '" + searchId + "'");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		ParkingVO vo = null;
		if (rs.next()) {
			String carNo = rs.getString("CAR_NO");
			vo = new ParkingVO();
			vo.setCarNo(carNo);
			
		}
		rs.close();
		stmt.close();
		conn.close();
		return vo;
	}
}
