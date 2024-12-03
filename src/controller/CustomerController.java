package controller;

import db.CustomerDB;
import db.CustomerDBIF;
import model.Customer;

public class CustomerController {
	private CustomerDBIF customerInterface;

	public CustomerController() throws GeneralException {
		customerInterface = new customerDB();
	}
	
	
	public Customer findCustomerByPhoneNo(String phoneNo)  {
		Customer foundCustomer = customerInterface.findCustomerByPhoneNo(phoneNo);
		return foundCustomer;
	}

}
