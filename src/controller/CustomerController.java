package controller;

import db.CustomerDB;
import db.CustomerDBIF;
import model.Customer;

public class CustomerController {
	private CustomerDBIF customerInterface;

	public CustomerController() throws DataAccessException {
		customerInterface = new CustomerDB();
	}
	
	
	public Customer findCustomerByPhoneNo(String phoneNo, boolean fullAssertion) throws DataAccessException  {
		Customer foundCustomer = customerInterface.findCustomerByPhoneNo(phoneNo, fullAssertion);
		return foundCustomer;
	}

}
