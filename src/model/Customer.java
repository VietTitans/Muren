package model;

public class Customer extends Person {
private int customerId; 

	public Customer(String fName, String lName, String phoneNo, String email, Address address, int customerId ) {
		super(fName, lName, phoneNo, email, address);
		this.customerId=customerId; 
	}
	public Customer(String fName, String lName, String phoneNo, String email, Address address ) {
		super(fName, lName, phoneNo, email, address);
	}
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
