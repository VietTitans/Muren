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
	public Customer() {
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String getfName() {
		return super.getfNameSub();
	}
	@Override
	public void setfName(String fName) {
		super.setfNameSub(fName);
		
	}
	@Override
	public String getlName() {
		return super.getlNameSub();
	}
	@Override
	public void setlName(String lName) {
		super.setlNameSub(lName);
		
	}
	@Override
	public String getPhoneNo() {
		return super.getPhoneNoSub();
	}
	@Override
	public void setPhoneNo(String phoneNo) {
		super.setPhoneNoSub(phoneNo);
		
	}
	@Override
	public String getEmail() {
		return super.getEmailSub();
	}
	@Override
	public void setEmail(String email) {
		super.setEmailSub(email);
		
	}
	@Override
	public Address getAddress() {
		return super.getAddressSub();
	}
	@Override
	public void setAddress(Address address) {
		super.setAddressSub(address);
		
	}

}
