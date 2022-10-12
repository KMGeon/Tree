package HotelJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import oracle.jdbc.driver.OracleDriver;

public class Hotel {
	private Scanner scanner;
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public Hotel() {
		scanner = new Scanner(System.in);
	}
	
	public void hotelMenu() {
		System.out.println("************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃  3.객실상태  4.업무종료");
		System.out.println("************************************");
		System.out.print("메뉴선택 => ");
	}
	
	public static void main(String[] args) {
		new Hotel().hotelStart();
	}
	
	public void hotelStart() {
		
		while(true) {
			
			hotelMenu();
			
			int menuNum = scanner.nextInt();
			
			switch(menuNum) {
			case 1: 
				System.out.println();
				checkIn();
				break;
			case 2: 
				System.out.println();
				checkOut();
				break;
			case 3: 
				System.out.println();
				roomStatus();
				break;
			case 4: 
				System.out.println();
				System.out.println("****************");
				System.out.println("호텔 문을 닫습니다.");
				System.out.println("****************");
				return;
			
			}
		}
	}
	
	
	public void checkIn() {
		
		boolean chk = false;
		
		int roomNum = 0;
		
		do {
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			roomNum = scanner.nextInt();
			
			chk = checkRoom(roomNum);
			
			if(chk == true) {
				System.out.println("이미 체크인이 완료된 방입니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(chk == true);
		
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scanner.next();
				
		try {
			conn = JDBCUtil.getConnection();
			String sql = " insert into hotel_mng (room_num, guest_name) values (?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,roomNum);
			pstmt.setString(2, name);
			
			int cnt = pstmt.executeUpdate();
			
			
			if(cnt > 0) {
				System.out.println("체크인 완료");
			}else {
				System.out.println("체크인 블가");
			}		
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void checkOut() {
		
		try {
			conn = JDBCUtil.getConnection();
			
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			int roomNum = scanner.nextInt();
			
			String sql = "delete from hotel_mng where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크아웃 성공");
			}else {
				System.out.println("체크아웃 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}		
		
	}
	
	public void roomStatus() {
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from hotel_mng";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				String name = rs.getString("guest_name");
				System.out.println("방번호 : " + roomNum + ", 투숙객 : " + name);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}
	

	public boolean checkRoom(int roomNum) {
		
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " select count(*) cnt from hotel_mng where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return chk;
		
	}
	
	
}


