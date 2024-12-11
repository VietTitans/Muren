package model;

public abstract class Person {
	private String fName; 
	private String lName; 
	private String phoneNo; 
	private String email; 
	private Address address;
	
	public Person() {
		
	}
	public Person(String fName, String lName, String phoneNo, String email, Address address) {
		this.fName = fName;
		this.lName = lName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
	}
	public abstract String getfName();
	
	protected String getfNameSub() {
		return fName;
	}
	
	public abstract void setfName(String fName);
	
	protected void setfNameSub(String fName) {
		this.fName = fName;
	}
	
	public abstract String getlName();
	
	protected String getlNameSub() {
		return lName;
	}
	
	public abstract void setlName(String lName);
	
	protected void setlNameSub(String lName) {
		this.lName = lName;
	}
	
	public abstract String getPhoneNo();
	
	protected String getPhoneNoSub() {
		return phoneNo;
	}
	
	public abstract void setPhoneNo(String phoneNo);
	
	protected void setPhoneNoSub(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public abstract String getEmail();
	
	protected String getEmailSub() {
		return email;
	}
	
	public abstract void setEmail(String email);
	
	protected void setEmailSub(String email) {
		this.email = email;
	}
	
	public abstract Address getAddress();
	
	protected Address getAddressSub() {
		return address;
	}
	
	public abstract void setAddress(Address address);
	
	protected void setAddressSub(Address address) {
		this.address = address;
	}
	

}
