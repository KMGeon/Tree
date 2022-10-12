package chapter8Exercise;

public class Customer {
	//필드
	private String firstName;
	private String lastName;
	private BankAccount[] accounts;
	private int numberOfAccounts;
	
	//생성자
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		accounts = new BankAccount[5];
	}
	//메소드
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setAccount(BankAccount account) {
		this.accounts[numberOfAccounts] = accounts[numberOfAccounts];
	}
	public BankAccount getAccount() {
		return accounts[numberOfAccounts] = accounts[numberOfAccounts];
	}
	public void addAccount(BankAccount account) {
		this.accounts[numberOfAccounts++] = account;
	}
	public BankAccount getAccount(int index) {
		return accounts[index];
	}
	public int getNumberOfAccounts() {
		return this.numberOfAccounts;
	}
	public String toString() {
	return String.format(firstName+" "+lastName);
	}
	
}
