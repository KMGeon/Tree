package chapter7ExerciseAdvanced;

public class SavingsAccount extends BankAccount{
	//필드
	private double interestRate;
	
	//생성자
	public SavingsAccount(int balance,double interestRate) {
		super(balance);
		//this.balance=balance; 필요없음
		this.interestRate=interestRate;
	}
	
	//메소드
	
	public void updateBalance(int period) {
		this.balance = (int) ((balance*interestRate*period)+balance);
	} 
}
