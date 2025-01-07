package model;

public class Employee extends Person {

private int employeeId; 
private String cpr; 
private EmployeeType employeeType; 


public Employee() {
	
}
public Employee(String fName, String lName, String phoneNo, String email, Address address,EmployeeType employeeType, int employeeId, String cpr) {
	super(fName, lName, phoneNo, email, address);
	this.employeeType = employeeType;
	this.employeeId = employeeId;
	this.cpr= cpr;
	
}
public Employee(String fName, String lName, String phoneNo, String email, Address address,EmployeeType employeeType) {
	super(fName, lName, phoneNo, email, address);
	this.employeeType = employeeType;
	//this.employeeId = employeeId;
}


public int getEmployeeId() {
	return employeeId;
}


public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}


public String getCpr() {
	return cpr;
}


public void setCpr(String cpr) {
	this.cpr = cpr;
}


public EmployeeType getEmployeeType() {
	return employeeType;
}


public void setEmployeeType(EmployeeType employeeType) {
	this.employeeType = employeeType;
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