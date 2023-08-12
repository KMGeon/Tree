package chapter8Exercise;

public class CheckingAccount extends BankAccount{
	//필드
	private SavingsAccount protectedBy;
	
	//생성자
	public CheckingAccount(int balance) {
		super(balance);
	}
	public CheckingAccount(int balance, SavingsAccount protectedBy) {
		super(balance);
		this.protectedBy=protectedBy;
	}
	
	//메소드
	
	// SavingsAccount : 5,000원
	// CheckingAccount : 10,000원
	// 1. 백지수표로 7,000원 짜리 물건을 구매한 경우
	// 2. 백지수표로 13,000원 짜리 물건을 구매한 경우
	// 2번째 경우 CheckingAccount는 0으로 만든다. 
	// SavingsAccount - (amount-CheckingAccount)
	
	@Override
	public boolean withdraw(int amount) {
		if(super.withdraw(amount)) {
			return true;
		}else {
			protectedBy.withdraw(amount - this.balance);
			//같은코드 protectedBy.balance -= amount - this.balance;
			this.balance = 0;
			return false;
		}
	}
	@Override
	public String getAccountType() {
		return String.format("당좌예금");
	}
}
