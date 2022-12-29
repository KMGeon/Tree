package HighJava.src.JDBC;

import HighJava.src.JDBC.Util.JDBCUtil;

import javax.rmi.CORBA.Util;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.out;
/*
    #JDBC 실행순서
    1.드라이버 체크 (옵션)
    2.connection 객체 가져오기
    3.stateMent객체 생성 -> SQL쿼리 날리는 객체
    4.
 */

/*
	회원정보를 관리하는 프로그램을 작성하는데
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)

	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.

	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------


// 회원관리 프로그램 테이블 생성 스크립트
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
class T01MemberInfoTest {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private Scanner scan = new Scanner(System.in);

    T01MemberInfoTest() {
    }

    /**
     * 메뉴를 출력하는 메서드
     */
    public void displayMenu() {
        out.println();
        out.println("----------------------");
        out.println("  === 작 업 선 택 ===");
        out.println("  1. 자료 입력");
        out.println("  2. 자료 삭제");
        out.println("  3. 자료 수정");
        out.println("  4. 전체 자료 출력");
        out.println("  5. 작업 끝.");
        out.println("----------------------");
        out.print("원하는 작업 선택 >> ");
    }

    /**
     * 프로그램 시작메서드
     */
    public void start() throws SQLException {
        int choice;
        do {
            displayMenu(); //메뉴 출력
            choice = scan.nextInt(); // 메뉴번호 입력받기
            switch (choice) {
                case 1:  // 자료 입력
                    intertMember();
                    break;
                case 2:  // 자료 삭제
                    deleteMember();
                    break;
                case 3:  // 자료 수정
                    updateMember();
                    break;
                case 4:  // 전체 자료 출력
                    viewAll();
                    break;
                case 5:  // 작업 끝
                    out.println("작업을 마칩니다.");
                    break;
                default:
                    out.println("번호를 잘못 입력했습니다. 다시입력하세요");
            }
        } while (choice != 5);
        //회원정보를 추가하는 메서드
    }

    private void viewAll() {
        out.println();
        out.println("=================================================================");
        out.println("id \t 이름 \t 전화번호 \t\t 주소");
        out.println("=================================================================");

        try {
            conn = JDBCUtil.getConnection();
            String sql = " select * from mymember ";
            stmt = Objects.requireNonNull(conn).createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String memid = rs.getString("mem_id");
                String memName = rs.getString("mem_name");
                String memTel = rs.getString("mem_tel");
                String memAddr = rs.getString("mem_addr");

                out.println(memid + "\t" + memName + "\t" + memTel + "\t" + memAddr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteMember() {
//        String memId = "";
//        out.println();
//        out.println("삭제할 회원 정보를 입력하세요");
//        out.println("회원id>>");
//

        boolean chk = false;
        String memId = "";
        do {
            out.println();
            out.println("삭제할 회원 정보를 입력하세요");
            out.println("회원 id");

            //회원 아이디가 키값
            memId = scan.next();
            chk = checkMember(memId);

            if (chk != true) {
                out.println("회원id가" + memId + "인 회원은 없습니다..");
                out.println("다시 입력해주세요");
            }
        }
        while (chk != true);
        out.println("회원 이름>>");
        String memName = scan.next();

        try {
            conn = JDBCUtil.getConnection();
            String sql = " delete from mymember where mem_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);

            int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                out.println(memId + "회원정보 삭제");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, pstmt, rs);
        }

    }

    private void updateMember() {
        boolean chk = false;
        String memId = "";
        do {
            out.println();
            out.println("수정할 회원 정보를 입력하세요");
            out.println("회원 id");

            //회원 아이디가 키값
            memId = scan.next();
            chk = checkMember(memId);

            if (chk != true) {
                out.println("회원id가" + memId + "인 회원은 없습니다..");
                out.println("다시 입력해주세요");
            }
        }
        while (chk != true);
        out.println("회원 이름>>");
        String memName = scan.next();
        out.println(" 회원 전화번호>>");
        String memTel = scan.next();
        scan.nextLine(); //입력버퍼 지우기
        out.println("회원의 주소>>");
        String memAddr = scan.nextLine();

        try {
            conn = JDBCUtil.getConnection();
            String sql = " UPDATE mymember " +
                    " SET mem_name = ? " +
                    "    , mem_tel = ? " +
                    "   , mem_addr = ? " +
                    "  where mem_id =?  ";

            pstmt = Objects.requireNonNull(conn).prepareStatement(sql);
            pstmt.setString(1, memName);
            pstmt.setString(2, memTel);
            pstmt.setString(3, memAddr);
            pstmt.setString(4, memId);

            int cnt = pstmt.executeUpdate();

            if (cnt > 0) {
                out.println(memId + "회원 변경 작업 성공");
            } else {
                out.println(memId + "회원 변경 작업 실패!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, pstmt, rs);
        }

    }

    private void intertMember() throws SQLException {
        boolean chk = false;
        String memId = "";
        do {
            out.println();
            out.println("추가할 회원 정보를 입력하세요");
            out.println("회원 id");

            //회원 아이디가 키값
            memId = scan.next();
            chk = checkMember(memId);

            if (chk == true) {
                out.println("회원id가" + memId + "인 회원은 이미 존재합니다.");
                out.println("다시 입력해주세요");
            }
        }
        while (chk == true);
        out.println("회원 이름>>");
        String memName = scan.next();
        out.println(" 회원 전화번호>>");
        String memTel = scan.next();

        scan.nextLine(); //입력버퍼 지우기
        out.println("회원의 주소>>");
        String memAddr = scan.nextLine();

        try {
            conn = JDBCUtil.getConnection();
            String sql = " INSERT INTO mymember( mem_id, mem_name,mem_tel,mem_addr, REG_DT)"
                    + " VALUES(?,?,?,?,sysdate)";
            pstmt = Objects.requireNonNull(conn).prepareStatement(sql);
            pstmt.setString(1, memId);
            pstmt.setString(2, memName);
            pstmt.setString(3, memTel);
            pstmt.setString(4, memAddr);

            int cnt = pstmt.executeUpdate();

            if (cnt > 0) {
                out.println(memId + "회원 추가 작업 성공");
            } else {
                out.println(memId + "회원 추가 작업 실패!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, pstmt, rs);
        }
    }


    //회원아이디를 이용하여 존재하는지 알려주는 메서드
    //parm : memid / return true (회원존재) 없으면  false
    private boolean checkMember(String memId) {
        boolean chk = false;
        try {
            conn = JDBCUtil.getConnection();
            String sql = " select count(*) as cnt from mymember "
                    + " where mem_id=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            int cnt = 0;
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
            if (cnt > 0) {
                chk = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, pstmt, rs);
        }
        return chk;
    }


    public static void main(String[] args) throws SQLException {
        T01MemberInfoTest memObj = new T01MemberInfoTest();
        memObj.start();
    }

}






