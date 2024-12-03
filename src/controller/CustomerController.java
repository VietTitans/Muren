package controller;

import db.CustomerDB;
import db.CustomerDBIF;
import model.Customer;

public class CustomerController {

	private CustomerDBIF customerInterface;
	public CustomerController() throws GeneralException {
		customerInterface = new customerDB();
	}
	
	
	public Customer findCustomerByPhoneNo(PhoneNo)  {
		Customer foundCustomer = personInterface.findCustomerByPhoneNo(PhoneNo);
		return foundCustomer;
	}
}
