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
	void testCustomerDoesntExists() throws NullPointerException {
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
