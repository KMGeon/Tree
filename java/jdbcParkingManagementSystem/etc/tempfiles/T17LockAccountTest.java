package HighJava.src.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
은행의 입출금을 스레드로 처리하는 예제
(lock 객체를 이용한 동기화 처리)
 */
public class T17LockAccountTest {
    /*
    락(lock) 기능을 제공하는 클래스
    reentrantlock : read 및 write구문없이 사용하기 위한 락 클래스를 동기화 처리를 위해 사용한다.
    synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공 ex) Fairness 설정등.-> 가장 오래 기다린 스레드가 가장 먼저 락을 얻는다.
     */
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        LockAccount lAcc = new LockAccount(lock);

        BankThread2 bth1 = new BankThread2(lAcc);
        BankThread2 bth2 = new BankThread2(lAcc);

        bth1.start();
        bth2.start();


    }
}

//입출금을 담당하는 클래스(공유 객체)
class LockAccount {
    private int balance;
    //Lock 객체변수 생성 -> 되도록이면 private final로 설정한다.
    private final Lock lock;

    public LockAccount(ReentrantLock lock) {
        this.lock = (Lock) lock;
    }

    public int getBalance() {
        return balance;
    }

    public void desposit(int money) {
        /*
        lock 객체의 메소드가 동기화의 시작이고 unlock메소드가 동기화의 끝
        lock메서드로 동기화를 설정한 곳에서는 반드시 unlock 메서드로 해제해 주어야 한다.
         */
        lock.lock();//락설정 (락을 얻기 전까지 block상태)
        balance += money;//동기화 처리 부분
        lock.unlock();//락 해체
    }

    //출금하는 메서드 출금성공 : true 출금실패 false
    public boolean withdraw(int money) {
        boolean cnk = false;
        //try~catch 블럭을 사용할 경우에는 unlock()호출을 finally에서 하도록 한다.
        try {
            //락 설정하기
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "락 설정 얻기 완료...");
            if (balance >= money) {
                for (int i = 0; i < 100000; i++) {

                }
                balance -= money;
                System.out.println("메서드 안에서  balance = " + getBalance());
                cnk = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "락 해제 완료...");
        }
        return cnk;
    }
}

// 은행 업무를 처리하는 스레드
class BankThread2 extends Thread {
    private LockAccount lAcc;

    public BankThread2(LockAccount lAcc) {
        this.lAcc = lAcc;
    }

    @Override
    public void run() {
        boolean result = lAcc.withdraw(6000); // 6000원 인출
        System.out.println("스레드 안에서 result = " + result + ", balance = " + lAcc.getBalance());
    }
}