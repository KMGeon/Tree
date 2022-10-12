package chapter7Exercise;

public class Customer {
	//필드
	private String firstName;
	private String lastName;
	private BankAccount account;
	//생성자
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	//메소드
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setAccount(BankAccount account) {
		this.account = account;
	}
	public BankAccount getAccount() {
		return account;
	}
		
	public String toString() {
		return this.firstName + this.lastName +" 고객님에 " + "잔액은: " + account.getBalance() + " 입니다." ;
	}
	
}
