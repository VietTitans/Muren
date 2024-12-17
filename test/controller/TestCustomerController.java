package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.ResetDB;
import model.Customer;

class TestCustomerController {
	
	private CustomerController customerController;

	@BeforeEach
	void setUp() throws Exception {
		customerController = new CustomerController();
		ResetDB.main(null);
	}


	@Test
	void testFindCustomerByPhoneNo() throws DataAccessException {
		//Arrange
		//Customer John, Doe, 12345678 exists in the database
		//Act
		Customer result = customerController.findCustomerByPhoneNo("12345678", false);
		//Assert
		String expectedFName = "John";
		String expectedLName = "Doe";
		String expectedPhoneNo = "12345678";
		assertEquals(expectedFName, result.getfName());
		assertEquals(expectedLName, result.getlName());
		assertEquals(expectedPhoneNo, result.getPhoneNo());
	}
	
	@Test
	//Integration test
	void testCustomerDoesntExists() throws DataAccessException   {
		//Arrange
		String invalidPhoneNo = "66656666";
			//Act
			Customer customer = customerController.findCustomerByPhoneNo(invalidPhoneNo, false);
		
		//Assert
			assertNull(customer);
	}
	
}
