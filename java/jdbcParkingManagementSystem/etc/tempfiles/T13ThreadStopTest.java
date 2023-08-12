package HighJava.src.Thread;

import javafx.scene.paint.Stop;

public class T13ThreadStopTest {
    public static void main(String[] args) {
//        ThreadStopEx12 thread = new ThreadStopEx12();
//        thread.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //thread.stop();
//        thread.setStop(true);
        ThreadStopEx2 ex2 = new ThreadStopEx2();
        ex2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadStopEx12 extends Thread {
    private boolean stop;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {


        while (!stop) {
            System.out.println("스레드 처리중...");
        }
        System.out.println("자원 정리중...");
        System.out.println("실행 종료.");

    }
}

//interrupt() :방해하다
// 메서드를 이용하여 스레드를 멈추게 하는 방법


class ThreadStopEx2 extends Thread {
    @Override
    public void run() {
////방법1 -> sleep메서드나 join메서드 등을 사용했을때 interrupt()메서드를 호출하면
////interruptexception이 발생한다.
//        try {
//            while (true) {
//                System.out.println("쓰레드 처리중...");
//               // Thread.sleep(1);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //방법2 ->interrupt()메서드가 호출되었는지 검사하기
        while (true) {
            System.out.println("스레드 처리중");
            //검사방법1 -> 스레드의 인스턴스용 메서드를 이용하는 방법
//            if (this.isInterrupted()) {   //interrupt()호출되면 true리턴
//                System.out.println("인스턴스 메서드isInterrupted 호출됨");
//                break;
//            }

            //검사방법2 ->스레드의 정적 메서드를 이용하는 방법
            if(Thread.interrupted()){
                System.out.println("정적 메서드 interrupted 호출됨");
                break;
            }
        }
    }


}