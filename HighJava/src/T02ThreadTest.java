package Thread;

import HighJavaHomeWork.ThreadHw;

public class T02ThreadTest {
    public static void main(String[] args) {
        //멀티 스레드 프로그램 방식

        //방법1.  thread클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 실행한다.
        MyThread1 th1 = new MyThread1();
        th1.start();
        //방법2.Runnable인터페이스를 구현한 클래스의 인스턴스를 생성한 후 이 인스턴스를 thread객체의
        //인스턴스를 생성할 때 생성자의 파라미터로 넘겨준다. 이때 생성된 thread객체의 인스턴스의 start() 실행한다.
        Runnable r = new MyTread2();
        Thread th2 = new Thread(r);
        th2.start();

        //방법3. 익명클래스를 이용하는 방법 runnable인터페이스를 구현한 익명 클래스를 thread 인스턴스를 생성할때
        //매개변수로 넘겨준다.

//        Thread th3 = new Thread(new Runnable()) {
//            @Override
//            public void run() {
//                for (int i = 0; i < 200; i++) {
//                    System.out.println("*");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//            }
//        };
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.print("-");
            try {
                //Thread.sleep (시간) -> 주어진 시간동안 작업을 잠시 멈춘다. 1000은 1초 의미
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class MyTread2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("*");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}