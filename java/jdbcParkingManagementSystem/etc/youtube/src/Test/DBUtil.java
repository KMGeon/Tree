package Test;

class  Test{
    String url = "jdbc:mysql://localhost:3306/newlecture";
    String user = "newlecture";
    String pass = "111";

    public void inti(){

    }
}


//public class DBUtil {
//
//    Connection conn ;
//    Statement stmt ;
//    ResultSet rs ;
//
//    String url = "jdbc:mysql://localhost:3306/newlecture";
//    String user = "newlecture";
//    String pass = "111";
//
//    public DBUtil() {
//        conn = getConnection();
//    }
//
//    public Connection getConnection() {
//
//        Connection conn = null;
//
//        try {
//            // 1. 드라이버 세팅
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // 2. Connection 획득
//            conn = DriverManager.getConnection(url, user, pass);
//
//        } catch(Exception e) {
//            System.out.println("DB 작업중 문제 발생");
//            e.printStackTrace();
//        }
//
//        return conn;
//    }
//
//    public void insertAddress(String name, String address, String phone) {
//
//        try {
//            stmt = conn.createStatement();
//
//            String sql = "INSERT INTO address\r\n"
//                    + "SET `name` = '" + name + "',\r\n"
//                    + " address = '" + address + "',\r\n"
//                    + " phone = '" + phone + "'";
//
//            stmt.executeUpdate(sql);
//
//        } catch(Exception e) {
//            System.out.println("ADD DB작업중 문제 발생!!");
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<Addr> getAddresses() {
//
//        ArrayList<Addr> AddrList = new ArrayList<>();
//
//        try {
//            stmt = conn.createStatement();
//
//            String sql = "SELECT *\r\n"
//                    + "FROM t_address";
//
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String address = rs.getString("address");
//                String phone = rs.getString("phone");
//
//                Addr a1 = new Addr(id, name, address, phone);
//                AddrList.add(a1);
//            }
//        } catch(Exception e) {
//            System.out.println("list DB 작업중 문제 발생!!");
//            e.printStackTrace();
//        }
//
//        return AddrList;
//    }
//
//    public void updateAddress(int id, String name, String address, String phone) {
//        try {
//            stmt = conn.createStatement();
//
//            String sql = "UPDATE t_address\r\n"
//                    + "SET `name` = '" + name + "',\r\n"
//                    + "address = '" + address + "',\r\n"
//                    + "phone = '" + phone + "'\r\n"
//                    + "WHERE id = " + id;
//
//            stmt.executeUpdate(sql);
//
//        } catch(Exception e) {
//            System.out.println("ADD DB작업중 문제 발생!!");
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteAddress(int id) {
//        try {
//            stmt = conn.createStatement();
//
//            String sql = "DELETE FROM t_address\r\n"
//                    + "WHERE id = " + id;
//
//            stmt.executeUpdate(sql);
//
//        } catch(Exception e) {
//            System.out.println("ADD DB작업중 문제 발생!!");
//            e.printStackTrace();
//        }
//    }
//}