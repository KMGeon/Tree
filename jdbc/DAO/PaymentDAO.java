package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import VO.PaymentVO;

public class PaymentDAO {
	private PaymentDAO() {}
	private static PaymentDAO instance = new PaymentDAO();
	public static PaymentDAO getInstance() {
		return instance;
	}

	public int insertPay(String carNo) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO PAYMENT(PAY_NO, PAY_CARNO, PAY_IPTTIME, PAY_OPTTIME) ");
		builder.append("SELECT SEQ_PAYMENT.NEXTVAL, CAR_NO, PRK_IPTTIME, PRK_OPTTIME ");
		builder.append("FROM   PARKING ");
		builder.append("WHERE  CAR_NO = '" + carNo + "'");
		String sql = builder.toString();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public int updateFee(String carNo, int fee) throws Exception {
		Connection conn = DBUtil.getConnection();

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE PAYMENT ");
		builder.append("SET PAY_FEE = " + fee + " ");
		builder.append("WHERE PAY_CARNO = '" + carNo + "'");
		String sql = builder.toString();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int row = pstmt.executeUpdate(); // 성공한 쿼리의 갯수 반환
		pstmt.close();
		conn.close();
		return row;
	}

	public List<PaymentVO> drawSeachData(String searchId) throws Exception {
	      Connection conn = DBUtil.getConnection();
	      StringBuilder builder = new StringBuilder();
	      builder.append("SELECT ");
	      builder.append("PAY_CARNO   carNo ");
	      builder.append(",TO_CHAR(PAY_IPTTIME, 'YYYY\"년\" MONDD\"일\" HH24:MI')    prkiptTime ");
	      builder.append(",TO_CHAR(PAY_OPTTIME, 'YYYY\"년\" MONDD\"일\" HH24:MI')    prkOptTime ");
	      builder.append(",PAY_FEE        payFee ");
	      builder.append("FROM PAYMENT ");
	      builder.append("WHERE PAY_CARNO =? ");

	      String sql = builder.toString();
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setString(1, searchId);
	      ResultSet resultSet = stmt.executeQuery();
	      List<PaymentVO> payList = new ArrayList<>();
	      while (resultSet.next()) {
	         String carNo = resultSet.getString("carNo");
	         String iptTime = resultSet.getString("prkiptTime");
	         String optTime = resultSet.getString("prkOptTime");
	         int payFee = resultSet.getInt("payFee");
	         payList.add(new PaymentVO(carNo, iptTime, optTime, payFee));
	      }
	      resultSet.close();
	      stmt.close();
	      conn.close();
	      return payList;
	   }
	// 관리자 index page 1~3
    public List<PaymentVO> partialOutput(int startNo, int endNo) throws Exception { // carNo => 납부내역 리스트
       Connection conn = DBUtil.getConnection();
       StringBuilder builder = new StringBuilder();
       builder.append("SELECT ");
       builder.append("pay_carNo carNo ");
       builder.append(",TO_CHAR(pay_ipttime, 'YYYY\"년\" MONDD\"일\" HH24:MI') prkiptTime ");
       builder.append(",TO_CHAR(pay_opttime, 'YYYY\"년\" MONDD\"일\" HH24:MI') prkOptTime ");
       builder.append(",pay_fee payFee ");
       builder.append("FROM(select rownum as pm, payment.* from payment) ");
       builder.append("WHERE pm between ? and ? ");

       String sql = builder.toString();
       PreparedStatement stmt = conn.prepareStatement(sql);
       stmt.setInt(1, startNo);
       stmt.setInt(2, endNo);
       ResultSet resultSet = stmt.executeQuery();

       List<PaymentVO> payList = new ArrayList<>();
       while (resultSet.next()) {
          String carNo = resultSet.getString("carNo");
          String iptTime = resultSet.getString("prkiptTime");
          String optTime = resultSet.getString("prkOptTime");
          int payFee = resultSet.getInt("payFee");
          payList.add(new PaymentVO(carNo, iptTime, optTime, payFee));
       }
       resultSet.close();
       stmt.close();
       conn.close();
       return payList;
    }
}
