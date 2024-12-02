package model;

public class Employee extends Person {

private int employeeId; 
private String cpr; 
private EmployeeType employeeType; 


public Employee(String fName, String lName, String phoneNo, String email, Address address,EmployeeType employeeType, int employeeId) {
	super(fName, lName, phoneNo, email, address);
	this.employeeType = employeeType;
	this.employeeId = employeeId;
	
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
}