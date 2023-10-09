package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import VO.*;
import View.LoginView;

public class MemberDAO {
	private MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	public MemberVO login(String searchId, String searchPw) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  mem_id ");
		builder.append("     ,   mem_pw ");
		builder.append(" FROM    member ");
		builder.append(" WHERE   mem_id = '" + searchId + "'");
		builder.append(" AND   mem_pw = '" + searchPw + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 or 1 -> Mono
		MemberVO vo = null;
		if (resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String pw = resultSet.getString("mem_pw");
			vo = new MemberVO(id, pw);
			vo.setMemId(id);
			vo.setMemPw(pw);
		}
		resultSet.close();
		statement.close();
		conn.close();
		return vo;
	}

	public MemberVO findId(String searchtel) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  mem_tel ");
		builder.append("     ,   mem_id ");
		builder.append(" FROM    member ");
		builder.append(" WHERE   mem_tel = '" + searchtel + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 or 1 -> Mono
		MemberVO vo = null;
		if (resultSet.next()) {
			String tel = resultSet.getString("mem_tel");
			String id = resultSet.getString("mem_id");
			vo = new MemberVO();
			vo.setMemTel(tel);
			vo.setMemId(id);
		}
		resultSet.close();
		statement.close();
		conn.close();
		return vo;
	}

	public MemberVO findPw(String searchid, String searchtel) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  mem_id ");
		builder.append("     ,   mem_pw ");
		builder.append("     ,   mem_tel ");
		builder.append(" FROM    member ");
		builder.append(" WHERE   mem_id = '" + searchid + "'");
		builder.append(" AND     mem_tel = '" + searchtel + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 or 1 -> Mono
		MemberVO vo = null;
		if (resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String pw = resultSet.getString("mem_pw");
			String tel = resultSet.getString("mem_tel");
			vo = new MemberVO();
			vo.setMemId(id);
			vo.setMemPw(pw);
			vo.setMemTel(tel);
		}
		resultSet.close();
		statement.close();
		conn.close();
		return vo;
	}

	public int mypagePw(String pw, String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append("     SET");
		builder.append("         mem_pw = ?");
		builder.append(" WHERE");
		builder.append("     mem_id = ?");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, pw);
		statement.setString(2, id);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public MemberVO DuplicateId(String searchid) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  mem_id ");
		builder.append(" FROM    member ");
		builder.append(" WHERE   mem_id = '" + searchid + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 or 1 -> Mono
		MemberVO vo = null;
		if (resultSet.next()) {// 중복검사통과
			String id = resultSet.getString("mem_id");
			vo = new MemberVO();
			// 전역변수에 회원가입 아이디 넣기
			vo.setMemId(id);
		}
		resultSet.close();
		statement.close();
		conn.close();
		return vo;
	}

	public int membershipId(MemberVO vo) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO member ( ");
		builder.append("             mem_id, ");
		builder.append("             mem_gd, ");
		builder.append("             mem_in ");
		builder.append(" ) VALUES ( ");
		builder.append("     ?, ");
		builder.append("     '회원', ");
		builder.append("     1 ");
		builder.append(" ) ");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, vo.getMemId());
		// insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public int membershipPw(String pw) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member ");
		builder.append(" SET ");
		builder.append("         mem_pw = ? ");
		builder.append(" WHERE ");
		builder.append("         mem_in = 1 ");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, pw);
		// insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public int membershipName(String name) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append(" SET");
		builder.append("         mem_name = ?");
		builder.append(" WHERE");
		builder.append("         mem_in = 1");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, name);
		// insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public MemberVO DuplicateTel(String searchtel) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT MEM_TEL ");
		builder.append(" FROM MEMBER ");
		builder.append(" WHERE MEM_TEL = '" + searchtel + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		MemberVO vo = null;
		if (resultSet.next()) { // 중복검사통과
			String tel = resultSet.getString("mem_tel");
			vo = new MemberVO();
			vo.setMemTel(tel);

		}
		resultSet.close();
		statement.close();
		conn.close();
		return vo;
	}

	public int membershipTel(String tel) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append(" SET");
		builder.append("         mem_tel = ?");
		builder.append(" WHERE");
		builder.append("         mem_in = 1");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, tel);
		// insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public int membershipFinal() throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append(" SET ");
		builder.append("         mem_in = 0 ");
		builder.append(" WHERE ");
		builder.append("         mem_in = 1");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
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

	public List<MemberVO> mypageList() throws SQLException {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT ");
		builder.append("     mem_id,");
		builder.append("     mem_pw,");
		builder.append("     mem_name,");
		builder.append("     mem_tel");
		builder.append(" FROM ");
		builder.append("     member ");
		builder.append(" WHERE MEM_ID = '" + LoginView.memId + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<MemberVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String pw = resultSet.getString("mem_pw");
			String name = resultSet.getString("mem_name");
			String tel = resultSet.getString("mem_tel");
			list.add(new MemberVO(id, pw, name, tel));
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}

	public int mypageName(String name, String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append("     SET");
		builder.append("         mem_name = ?");
		builder.append(" WHERE");
		builder.append("     mem_id = ?");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, id);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public int mypageTel(String tel, String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append("     SET");
		builder.append("         mem_tel = ?");
		builder.append(" WHERE");
		builder.append("     mem_id = ?");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, tel);
		statement.setString(2, id);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public int mypageDelete(String memId) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" DELETE FROM");
		builder.append(" member ");
		builder.append(" WHERE");
		builder.append("     mem_id = '" + memId + "'");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		// insert, update, delete가 모두 executeUpdate()메소드를 호출
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}

	public List<MemberVO> adminlist() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		// 1. + 기호로 문자열 결합
		// 2. StringBuilder(StringBuffer): 속도가 빠르다
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		builder.append("     mem_id,");
		builder.append("     mem_pw,");
		builder.append("     mem_name,");
		builder.append("     mem_tel");
		builder.append(" FROM");
		builder.append("     member");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 ~ 무한대 -> flux
		List<MemberVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String pw = resultSet.getString("mem_pw");
			String name = resultSet.getString("mem_name");
			String tel = resultSet.getString("mem_tel");
			list.add(new MemberVO(id, pw, name, tel));
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}

	public List<MemberVO> adminSearchList(String searchId) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement statement = conn.createStatement();
		// 1. + 기호로 문자열 결합
		// 2. StringBuilder(StringBuffer): 속도가 빠르다
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		builder.append("     mem_id,");
		builder.append("     mem_pw,");
		builder.append("     mem_name,");
		builder.append("     mem_tel");
		builder.append(" FROM");
		builder.append("     member");
		builder.append(" WHERE");
		builder.append("     mem_id ='" + searchId + "'");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		// 결과: 0 ~ 무한대 -> flux
		List<MemberVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String pw = resultSet.getString("mem_pw");
			String name = resultSet.getString("mem_name");
			String tel = resultSet.getString("mem_tel");
			list.add(new MemberVO(id, pw, name, tel));
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}

	public List<MemberVO> partialOutput(int startNo, int endNo) throws Exception { // carNo => 납부내역 리스트
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT A.MEM_ID AS ID ");
		builder.append("     , A.MEM_PW AS PW ");
		builder.append("     , A.MEM_NAME AS NAME ");
		builder.append("     , A.MEM_TEL AS TEL ");
		builder.append("     , B.CAR_NO AS CARNO ");
		builder.append("FROM  (SELECT ROWNUM AS R, MEMBER.* FROM MEMBER) A, CAR B ");
		builder.append("WHERE  B.MEM_ID = A.MEM_ID ");
		builder.append("AND    R BETWEEN ? AND ? ");

		String sql = builder.toString();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, startNo);
		stmt.setInt(2, endNo);
		ResultSet resultSet = stmt.executeQuery();

		List<MemberVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("ID");
			String pw = resultSet.getString("PW");
			String name = resultSet.getString("NAME");
			String tel = resultSet.getString("TEL");
			String carNo = resultSet.getString("CARNO");
			list.add(new MemberVO(id, pw, name, tel, carNo));
		}
		resultSet.close();
		stmt.close();
		conn.close();
		return list;
	}

	public int mypagecorrection(String name, String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append("     SET");
		builder.append("         mem_name = ?");
		builder.append(" WHERE");
		builder.append("     mem_id = ?");
		String sql = builder.toString();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, id);
		int executeUpdate = statement.executeUpdate();
		statement.close();
		conn.close();
		return executeUpdate;
	}
}
