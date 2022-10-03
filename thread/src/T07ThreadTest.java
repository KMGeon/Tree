package HighJava.src.Thread;

import javax.swing.*;
import java.util.Random;

public class T07ThreadTest {
    // 입력여부를 확인하기 위한 변수 선언
    public static boolean inputCheck = false;

    public static void main(String[] args) {
        Random random = new Random(1);
        String[] str = {"가위","바위","보"};

        DataInput1 input = new DataInput1();
        CountDown1 countdown = new CountDown1();
    input.start();
    countdown.start();


    }

}

/*
 * 데이터 입력을 받는 스레드 클래스스
 */
class DataInput1 extends Thread {

    @Override
    public void run() {
        Random rdNum = new Random(1);
        T06ThreadTest.inputCheck = true;
        String str = JOptionPane.showInputDialog("입력하세요");
        System.out.println("입력한 값은 " + str + "입니다.");
        //1:보 2:찌 3:묵
    if(str.equals("가위")&&rdNum.equals("1")||str.equals("바위")&&rdNum.equals("2")||str.equals("보")&&rdNum.equals("3")){
        System.out.println("승리");
    }

    }
}


/*카운트다운을 처리하는 스레드
 */
class CountDown1 extends Thread {

    @Override
    public void run() {
        for(int i=0;i>=1;i--) {
            if(T06ThreadTest.inputCheck) {
                return;
            }
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //10;포가 경과 되었는데도 입력이 없으면 ㅡ로그램을 종료한다.
        System.out.println("10초가 지났습니다. 프로그램 종료합니다.");
        System.exit(0);


    }
}
