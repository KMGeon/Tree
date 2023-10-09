package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import VO.PSVO;

public class PSDAO {
	private PSDAO() {}
	private static PSDAO instance = new PSDAO();
	public static PSDAO getInstance() {
		return instance;
	}
	
	public List<PSVO> psList() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT PS.PS_NO AS PS ");
		builder.append("     , P.PS_NO AS PRK ");
		builder.append("FROM   PS PS, PARKING P ");
		builder.append("WHERE  PS.PS_NO = P.PS_NO(+) ");
		builder.append("ORDER  BY 1");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		List<PSVO> mList = new ArrayList<PSVO>();
		while(rs.next()) {
			String psNo = rs.getString("PS");
			String psLoc = rs.getString("PRK");
			mList.add(new PSVO(psLoc, psNo));
		}
		rs.close();
		stmt.close();
		conn.close();
		return mList;
	}
	public List<PSVO> psListX() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT PS_NO ");
		builder.append("     , PS_LOC ");
		builder.append("FROM   PS ");
		String sql = builder.toString();
		ResultSet rs = stmt.executeQuery(sql);
		List<PSVO> mList = new ArrayList<PSVO>();
		while(rs.next()) {
			String psNo = rs.getString("PS_NO");
			String psLoc = rs.getString("PS_LOC");
			mList.add(new PSVO(psLoc, psNo));
		}
		rs.close();
		stmt.close();
		conn.close();
		return mList;
	}
	
	public int updatePs(String carNo, String psNo) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		String sql = "UPDATE PS SET PS_LOC = ? WHERE PS_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, carNo);
		pstmt.setString(2, psNo);
		int row = pstmt.executeUpdate(); //성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}
	public int deletePs(String carNo) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE PS ");
		builder.append("SET PS_LOC = NULL ");
		builder.append("WHERE PS_LOC = ?");
		String sql = builder.toString();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, carNo);
		int row = pstmt.executeUpdate(); //성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}
}
