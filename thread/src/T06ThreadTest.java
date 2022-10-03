package HighJava.src.Thread;

import javax.swing.*;

public class T06ThreadTest {
    // 입력여부를 확인하기 위한 변수 선언
    public static boolean inputCheck = false;

    public static void main(String[] args) {
        DataInput input = new DataInput();
        CountDown countdown = new CountDown();



    }

}

/*
 * 데이터 입력을 받는 스레드 클래스스
 */
class DataInput extends Thread {

    @Override
    public void run() {
        T06ThreadTest.inputCheck = true;
        String str = JOptionPane.showInputDialog("아무거나 입력하세요");
        System.out.println("입력한 값은 " + str + "입니다.");


    }
}


/*카운트다운을 처리하는 스레드
 */
class CountDown extends Thread {

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
