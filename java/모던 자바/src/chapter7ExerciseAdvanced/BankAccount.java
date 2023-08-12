package chapter7ExerciseAdvanced;

public class BankAccount {
	//필드
	protected int balance;
	
	//생성자
	public BankAccount(int balance){
		this.balance = balance;	
	}
	
	//메소드
	public int getBalance() {
		return balance;
	}
	public void deposit(int amount) {
		this.balance += amount; 
	}
	public boolean withdraw(int amount) {
		if(balance-amount>=0) {
			this.balance -= amount;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean transfer(int amount, BankAccount otherAccount) {
		if(withdraw(amount)==true) {
			otherAccount.deposit(amount);
			return true;
		}else {
			return false;
		}
	}
	public String toString() {
		return String.format("%d",balance);
	}
	
}
