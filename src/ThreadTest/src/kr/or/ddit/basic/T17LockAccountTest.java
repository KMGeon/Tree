package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Lock 객체를 이용한 동기화 처리)
 * 
 * */


public class T17LockAccountTest {
/*
 * 락(Lock) 기능을 제공하는 클래스 
 * 
 * ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락 클래스로 동기화 처리를 위해
 * 				사용한다. synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공된다.
 * 				ex) Fairness 설정등. => 가장 오래 기다린 스레드가 가장 먼저 락을 획득하게 함.
 * 
 * ReentrantReadWriteLock 
 * */
	public static void main(String[] args) {
		
		ReentrantLock lock = new ReentrantLock(true); // 락 객체 생성
		
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		BankThread2 bth1 = new BankThread2(lAcc);
		BankThread2 bth2 = new BankThread2(lAcc);
		
		bth1.start();
		bth2.start();
		
		
	}
}

// 입출금을 담당하는 클래스(공유객체)
class LockAccount {
	private int balance;
	
	// Lock 객체변수 생성 => 되도록이면 private final로 설정한다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}

	public int getBalance() {
		return balance;
	}
	
	// 입금하는 메소드
	public void deposit(int money) {
		// Lock 객체의 lock()메소드가 동기화의 시작이고, unlock()메소드가
		// 동기화의 끝을 나타낸다.
		// lock() 메소드로 동기화를 설정한 곳에서 반드시 unlock()메소드로 
		// 해제해 주어야 한다.
		lock.lock(); // 락 설정(락을 획득하기 전가지 BLOCKED 상태가 됨.)
		balance += money; // 동기화 처리 부분
		lock.unlock(); // 락 해제
		
	}
	
	// 출금하는 메소드 (출금 성공 : true, 출금 실패 : false 반환함)
	
	public boolean withdraw(int money) {
		boolean chk = false;
		
		// try ~ catch 블럭을 사용할 경우에는 unlock() 호출을 finally에서 하도록 한다.
		
		try {
			// 락 설정하기
			lock.lock();
			System.out.println(Thread.currentThread().getName()+"락 설정(획득) 완료...");
			if(balance >= money) {
				for(int i=1; i<=1000000000; i++){} // 시간 때우기 
				balance -= money;
				System.out.println("메소드 안에서 balance = " + getBalance());
					
				chk = true;
				
			}
		} catch (Exception e) {
			chk = false;
		}finally {
			lock.unlock(); // 락 해제
			System.out.println(Thread.currentThread().getName() + " 락 해제(반납) 완료 ...");
		}
		
		return chk;
		
	}
	
}

//은행업무를 처리하는 스레드
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