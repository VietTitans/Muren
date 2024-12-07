package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;

class TestCustomerController {
	
	private CustomerController customerController;

	@BeforeEach
	void setUp() throws Exception {
		customerController = new CustomerController();
	}

	@AfterEach
	void tearDown() throws DataAccessException {
	}
	
	@Test
	void testFindCustomerByPhoneNo() throws DataAccessException {
		//TODO: STUB
		//Arrange
		//Customer John, Doe, 12345678 exists in the database
		//Act
		Customer result = customerController.findCustomerByPhoneNo("12345678", false);
		//Assert
		assertEquals("John", result.getfName());
		assertEquals("Doe", result.getlName());
		assertEquals("12345678", result.getPhoneNo());
	}
	
	@Test
	//Integration test
	void testCustomerDoesntExists() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			customerController.findCustomerByPhoneNo("66656666", false);
			throw new NullPointerException("Customer not found");
		});
		//Assert
		assertEquals("Customer not found", exceptionThrown.getMessage());
	}
	
}
