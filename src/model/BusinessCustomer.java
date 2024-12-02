package model;

public class BusinessCustomer extends Customer {
private String cvrNo; 
private String businessName; 

	public BusinessCustomer(String fName, String lName, String phoneNo, String email, Address address, int customerId, String cvrNo, String businessName) {
		super(fName, lName, phoneNo, email, address, customerId);
		this.cvrNo= cvrNo; 
		this.businessName= businessName; 
	}

	public String getCvrNo() {
		return cvrNo;
	}

	public void setCvrNo(String cvrNo) {
		this.cvrNo = cvrNo;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	

}
