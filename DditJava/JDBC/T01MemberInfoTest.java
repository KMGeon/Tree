//package HighJava.src.JDBC;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//import java.util.logging.LogManager;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import util.DBUtil;
//
///*
//	회원을 관리하는 프로그램을 작성하는데
//	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
//	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
//
//	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
//
//	예시메뉴)
//	----------------------
//		== 작업 선택 ==
//		1. 자료 입력			---> insert
//		2. 자료 삭제			---> delete
//		3. 자료 수정			---> update
//		4. 전체 자료 출력	---> select
//		5. 작업 끝.
//	----------------------
//
//
//	// 회원관리 프로그램 테이블 생성 스크립트
//create table mymember(
//    mem_id varchar2(8) not null,  -- 회원ID
//    mem_name varchar2(100) not null, -- 이름
//    mem_tel varchar2(50) not null, -- 전화번호
//    mem_addr varchar2(128),    -- 주소
//    reg_dt DATE DEFAULT sysdate, -- 등록일
//    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
//);
//
//
//*/
//public class T01MemberInfoTest {
//    private Connection conn;
//    private Statement stmt;
//    private PreparedStatement pstmt;
//    private ResultSet rs;
//
//    private Scanner scan = new Scanner(System.in);
//
//    // log4j2 단독으로 직접 이용한 로그를 남기기 위한 로거 생성
//    private static final Logger SQL_LOGGER = LogManager.getLogger("log4jexam.sql.Query");
//    private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam.sql.Parameter");
//    private static final Logger RESULT_LOGGER = LogManager.getLogger(T01MemberInfoTest.class);
//
//    // apache commong-logging 이용한 로거생성
//    //private static final Log sqllogger = LogFactory.getLog("log4jexam.sql.Query");
//    //private static final Log paramlogger = LogFactory.getLog("log4jexam.sql.Parameter");
//    //private static final Log resultlogger = LogFactory.getLog(T05_MemberInfoTest.class);
//
//    // SL4J를 이용한 로거생성
//    //private static final Logger sqllogger = LoggerFactory.getLogger("log4jexam.sql.Query");
//    //private static final Logger paramlogger = LoggerFactory.getLogger("log4jexam.sql.Parameter");
//    //private static final Logger resultlogger = LoggerFactory.getLogger(T05_MemberInfoTest.class);
//
//    // 메뉴를 출력하는 메서드
//    public void displayMenu(){
//        System.out.println();
//        System.out.println("----------------------");
//        System.out.println("  === 작 업 선 택 ===");
//        System.out.println("  1. 자료 입력");
//        System.out.println("  2. 자료 삭제");
//        System.out.println("  3. 자료 수정");
//        System.out.println("  4. 전체 자료 출력");
//        System.out.println("  5. 작업 끝.");
//        System.out.println("----------------------");
//        System.out.print("원하는 작업 선택 >> ");
//    }
//
//    // 작업을 시작하는 메서드
//    public void start(){
//        int choice;
//        do{
//            displayMenu();
//            choice = scan.nextInt();
//            switch(choice){
//                case 1 :  // 자료 입력 순서1
//                    insertMember();
//                    break;
//                case 2 :  // 자료 삭제  순서4
//                    deleteMember();
//                    break;
//                case 3 :  // 자료 수정 순서3
//                    updateMemeber();
//                    break;
//                case 4 :  // 전체 자료 출력  순서2
//                    displayMemberAll();
//                    break;
//                case 5 :  // 작업 끝
//                    System.out.println("작업을 마칩니다.");
//                    break;
//                default :
//                    System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
//            }
//        }while(choice!=5);
//    }
//
//    /**
//     *  회원 추가하는 메서드
//     */
//    public void insertMember(){
//
//        boolean chk = false;
//        String memId;
//        do{
//            System.out.println();
//            System.out.println("추가할 회원 정보를 입력하세요.");
//            System.out.print("회원 ID >> ");
//            memId = scan.next();
//            chk = checkMember(memId);
//            if(chk==true){
//                System.out.println("회원ID가 " + memId + "인 회원은 이미 존재합니다.");
//                System.out.println("다시입력하세요");
//            }
//        }while(chk==true);
//
//        System.out.print("회원 이름 >> ");
//        String memName = scan.next();
//
//        System.out.print("회원 전화번호 >> ");
//        String memTel = scan.next();
//
//        scan.nextLine(); // 입력 버퍼 비우기
//        System.out.print("회원 주소 >> ");
//        String memAddr = scan.nextLine();
//
//        try {
//            conn = DBUtil.getConnection();
//            String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
//                    + " values (?,?,?,?) ";
//
//            SQL_LOGGER.debug("쿼리 : {}", sql);
//
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memId);
//            pstmt.setString(2, memName);
//            pstmt.setString(3, memTel);
//            pstmt.setString(4, memAddr);
//
//            PARAM_LOGGER.debug("mem_id: " + memId
//                    + "mem_name: " + memName
//                    + "memTel: " + memTel
//                    + "memAddr: " + memAddr);
//
//            int cnt = pstmt.executeUpdate();
//
//            RESULT_LOGGER.info("실행결과 : {}", cnt);
//
//            if(cnt>0){
//                System.out.println(memId + "회원 추가 작업 성공");
//            }else{
//                System.out.println(memId + "회원 추가 작업 실패!!");
//            }
//
//			/*
//			 	SQL Injection 관련 예제
//
//			 - 정상 쿼리 실행 시 ...
//			 String memId = "a001";
//			 String memPass = "java";
//
//			 String sql = "select * from member where mem_id = '"  + memId + "' and mem_pass = '" + memPass + "'";
//			            => select * from member where mem_id = 'a001' and mem_pass = 'java'
//
//			 - SQL Injection 공격 후...
//			 String memId = "' or 1=1 --";
//			 String memPass = "아무거나";
//			  => select * from member where mem_id = '' or 1=1 --' and mem_pass = '아무거나'
//
//			*/
//
//        } catch (SQLException e) {
//            System.out.println(memId + "회원 추가 작업 실패!!");
//            e.printStackTrace();
//        } finally {
//            disConnect();
//        }
//
//    }
//
//
//    /**
//     * 회원 ID를 이용하여 회원이 있는지 알려주는 메서드
//     * @param memId
//     * @return
//     */
//    public boolean checkMember(String memId){
//        boolean check = false;
//        try {
//            conn = DBUtil.getConnection();
//            String sql = "select count(*) cnt from mymember "
//                    + "where mem_id=?";
//            SQL_LOGGER.debug("쿼리 : " + sql);
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memId);
//
//            PARAM_LOGGER.debug("mem_id: " + memId);
//
//            rs = pstmt.executeQuery();
//            int count = 0;
//            if(rs.next()){
//                count = rs.getInt("cnt");
//            }
//            if(count>0){
//                check = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            check = false;
//        } finally{
//            disConnect();
//        }
//        return check;
//    }
//
//    /**
//     * 전체 회원을 출력하는 메서드
//     */
//    public void displayMemberAll(){
//        System.out.println();
//        System.out.println("--------------------------------------------------------");
//        System.out.println(" ID\t이 름\t전화번호\t\t주  소");
//        System.out.println("--------------------------------------------------------");
//
//        try {
//            conn = DBUtil.getConnection();
//            //conn = DBUtil2.getConnection();
//            //conn = DBUtil3.getConnection();
//            String sql = "select * from mymember";
//
//            SQL_LOGGER.debug("쿼리 : " + sql);
//
//            stmt = conn.createStatement();
//
//            rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                String memId = rs.getString("mem_id");
//                String memName = rs.getString("mem_name");
//                String memTel = rs.getString("mem_tel");
//                String memAddr = rs.getString("mem_addr");
//                System.out.println(memId + "\t" + memName + "\t"
//                        +  memTel + "\t\t" + memAddr);
//            }
//            System.out.println("--------------------------------------------------------");
//            System.out.println("출력작업 끝..");
//        } catch (SQLException e) {
//            System.out.println("회원 자료 가져오기 실패!!");
//            e.printStackTrace();
//        } finally {
//            disConnect();
//        }
//    }
//
//    /**
//     * 회원 정보를 수정하는 메서드
//     */
//    public void updateMemeber(){
//        System.out.println();
//        String memId = "";
//        boolean chk = true;
//        do{
//            System.out.print("수정할 회원ID를 입력하세요 >> ");
//            memId = scan.next();
//
//            chk = checkMember(memId);
//            if(chk==false){
//                System.out.println(memId + "회원은 없는 회원입니다.");
//                System.out.println("수정할 자료가 없으니 다시 입력하세요.");
//            }
//        }while(chk==false);
//
//        System.out.println("수정할 내용을 입력하세요.");
//        System.out.print("새로운 회원 이름 >> ");
//        String memName = scan.next();
//
//        System.out.print("새로운 회원 전화번호 >> ");
//        String memTel = scan.next();
//
//        scan.nextLine();
//        System.out.print("새로운 회원 주소 >> ");
//        String memAddr = scan.nextLine();
//
//        try {
//            conn = DBUtil.getConnection();
//            String sql = "update mymember set mem_name=?, "
//                    + "mem_tel=?, mem_addr=? "
//                    + "where mem_id=? ";
//
//            SQL_LOGGER.debug("쿼리 : " + sql);
//
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memName);
//            pstmt.setString(2, memTel);
//            pstmt.setString(3, memAddr);
//            pstmt.setString(4, memId);
//
//            PARAM_LOGGER.debug("mem_id: " + memId
//                    + "mem_name: " + memName
//                    + "memTel: " + memTel
//                    + "memAddr: " + memAddr);
//
//            int cnt = pstmt.executeUpdate();
//            if(cnt>0){
//                System.out.println(memId + "회원의 정보를 수정했습니다.");
//            }else{
//                System.out.println(memId + "회원 수정 실패!!");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            disConnect();
//        }
//    }
//
//
//    /**
//     * 회원 정보를 삭제하는 메서드(입력받은 회원ID를 이용하여 삭제한다.)
//     */
//    public void deleteMember(){
//        System.out.println();
//        System.out.print("삭제할 회원ID를 입력하세요 >> ");
//        String memId = scan.next();
//
//        try {
//            conn = DBUtil.getConnection();
//            String sql = "delete from mymember where mem_id=?";
//
//            SQL_LOGGER.debug("쿼리 : {}", sql);
//
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memId);
//
//            PARAM_LOGGER.debug("mem_id: {}", memId );
//
//            int cnt = pstmt.executeUpdate();
//            if(cnt>0){
//                System.out.println(memId + "회원 삭제 성공!");
//            }else{
//                System.out.println(memId + "회원 삭제 실패!");
//            }
//        } catch (SQLException e) {
//            System.out.println(memId + "회원을 삭제하지 못했습니다.");
//            e.printStackTrace();
//        } finally{
//            disConnect();
//        }
//    }
//
//
//    // 사용한 자원을 반납하는 메서드
//    public void disConnect(){
//        if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
//        if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
//        if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
//        if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
//    }
//
//    public static void main(String[] args) {
//        T01MemberInfoTest memObj = new T01MemberInfoTest();
//        memObj.start();
//    }
//}
//
//
//
//
//
//
