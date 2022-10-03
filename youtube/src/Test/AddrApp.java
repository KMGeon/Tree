package Test;


import java.util.ArrayList;
import java.util.Scanner;

public class AddrApp {

    public static void main(String[] args) {

        DBUtil db = new DBUtil();
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.print("명령어를 입력해주세요 : ");
            String cmd = scan.nextLine();

            if(cmd.equals("exit")) {
                break;

            } else if(cmd.equals("add")) {
                System.out.println("========= 주소록 등록 =========");
                System.out.print("이름 : ");
                String name = scan.nextLine();
                System.out.print("주소 : ");
                String address = scan.nextLine();
                System.out.print("전화번호 : ");
                String phone = scan.nextLine();

                db.insertAddress(name, address, phone);

                System.out.println("주소록 등록 완료.");
                System.out.println("============================");

            } else if(cmd.equals("list")) {

                ArrayList<Addr> addrList = db.getAddresses();
                WebView wv = new WebView();
                wv.printAddr(addrList);


            } else if(cmd.equals("update")) {
                System.out.print("몇번 주소록을 수정하시겠습니까 : ");
                int id = Integer.parseInt(scan.nextLine());
                System.out.println("========= 주소록 수정 =========");
                System.out.print("이름 : ");
                String name = scan.nextLine();
                System.out.print("주소 : ");
                String address = scan.nextLine();
                System.out.print("전화번호 : ");
                String phone = scan.nextLine();

                db.updateAddress(id, name, address, phone);

                System.out.println("주소록 수정 완료.");
                System.out.println("============================");

            } else if(cmd.equals("delete")) {
                System.out.print("몇번 주소록을 삭제하시겠습니까 : ");
                int id = Integer.parseInt(scan.nextLine());
                db.deleteAddress(id);
                System.out.println(id + "번 주소록이 삭제되었습니다.");
                System.out.println("==============================");
            }
        }
    }
}