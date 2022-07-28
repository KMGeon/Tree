package HighJava.src.Thread;

public class T11DisplayCharacterTest {
    static String strRank = ""; //순위 정보 담기 위한 변수선언

    public static void main(String[] args) {
        /*
        3개의 스레드가 각각 알파벳 대문자를 출력하는데  출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기.
         */


        DIsplayCharacter[] dIsplay = new DIsplayCharacter[]{
                new DIsplayCharacter("유재석"),
                new DIsplayCharacter("하하"),
                new DIsplayCharacter("송지효"),
                new DIsplayCharacter("김종국"),
        };

        for (DIsplayCharacter dc : dIsplay) {
            dc.start();
        }
        for (DIsplayCharacter dc : dIsplay) {
            try {
                dc.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("경기 끝...");
        System.out.println("---------------------------------");
        System.out.println();
        System.out.println("경기 결과");
        System.out.println("순위: "+strRank);
    }
}

//알파벳 대문자를 출력하는 스레드 클래스
class DIsplayCharacter extends Thread {
    private String name;

    public DIsplayCharacter(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            System.out.println(name + "의 출력 문자: " + ch);
            try {
                //sleep()메서드의 값을 200~500t사이의 난수로 한다.
                Thread.sleep((int) Math.random() + 301 + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + "출력 끝...");

        T11DisplayCharacterTest.strRank += name + " ";
    }
}
/*
1.스레드 홀스 클래스 만들기

 */