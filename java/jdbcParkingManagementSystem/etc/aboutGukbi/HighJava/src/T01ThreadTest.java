package Thread;

public class T01ThreadTest {
    public static void main(String[] args) {
        //싱글 스레드 프로그램
        for(int i=1; i<=200;i++){
            System.out.println("*");
        }
        System.out.println();
        for (int i=1;i<=200;i++){
            System.out.println("$");
        }
    }
}
