package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Customer;

class TestCustomerDB {

	@Test
	void testFindCustomerByPhoneNo() throws IllegalArgumentException, DataAccessException {
		//Arrange
		CustomerDB customerDB = new CustomerDB();
		Customer expectedResult = new Customer();
			expectedResult.setfName("John");
			expectedResult.setlName("Doe");
			expectedResult.setPhoneNo("12345678");
			expectedResult.setEmail("john.doe@example.com");
		//Act
		Customer result = customerDB.findCustomerByPhoneNo("12345678", false);
		//Assert
		assertEquals("John", result.getfName());
		assertEquals("Doe", result.getlName());
		assertEquals("12345678", result.getPhoneNo());
		assertEquals("john.doe@example.com", result.getEmail());
	}
	

}
