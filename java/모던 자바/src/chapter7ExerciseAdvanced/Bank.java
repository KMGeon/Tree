package chapter7ExerciseAdvanced;

public class Bank {
	//필드
	private Customer[] customers;
	private int numberOfCustomer;
	
	//생성자
	public Bank() {
		
		this.customers = new Customer[10];
	}
	
	//메소드
	public void addCustomer(Customer customer) {
		this.customers[numberOfCustomer++] = customer;
	}
	public int getNumberOfCustomers() {
		return numberOfCustomer;
	}
	
	public Customer[] getCustomers() {
		return customers;	
	}
	public Customer getCustomer(int index) {
		return customers[index];	
	}
	
}
