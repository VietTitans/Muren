package db;

import model.Customer;

public interface CustomerDBIF {
	
	
	public Customer findCustomerByPhoneNo(String PhoneNo);

}
