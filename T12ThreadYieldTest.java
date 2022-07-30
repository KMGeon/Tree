package HighJava.src.Thread;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class T12ThreadYieldTest {
    /*
    yield메서드에 대하여
    1.현재 실행 대기중인 동등한 우선순위 이상의 다른 스레드에게 실행 기회를 준다. (양보)
    2.현재  실행중인 스레드의 상태를  runnable상태로 바꾼다.(Waiting이나 Blocked상태로 바뀌지 않는다.)
    3.Yield()메서드를 실행한다고 해서 현재 실행중인 스레드가 곧바로 Runnable상태로 전의 된다고 확신할 수 없다.
     */
    public static void main(String[] args) {
        YieldThreadEx1 threadEx1 = new YieldThreadEx1();
        YieldThreadEx2 threadEx2 = new YieldThreadEx2();

        threadEx1.start();
        threadEx2.start();
    }
}

class YieldThreadEx1 extends Thread {
    public YieldThreadEx1() {
        super("양보클래스");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            Thread.yield();//양보하기기
        }
    }
}

class YieldThreadEx2 extends Thread {
    public YieldThreadEx2() {
        super("비양보클래스");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}