package db;

import controller.DataAccessException;
import model.Customer;

public interface CustomerDBIF {
	
	
	public Customer findCustomerByPhoneNo(String PhoneNo, boolean fullAssociation) throws DataAccessException;

}
