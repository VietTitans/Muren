package db;

import controller.DataAccessException;
import model.Customer;

public interface CustomerDBIF {
	
	
	public Customer findCustomerByPhoneNo(String PhoneNo, boolean fullAssociation) throws DataAccessException;

	public Customer findCustomerByCustomerNo(int customerNo, boolean fullAssociation) throws DataAccessException;

}
