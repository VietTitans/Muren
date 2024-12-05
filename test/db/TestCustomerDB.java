package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Customer;

class TestCustomerDB {

	static CustomerDB customerDB;
	private DBConnection connection;

	@BeforeAll
	void setUp() throws Exception {
		customerDB = new CustomerDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
		
	}

	@AfterAll
	void tearDown() throws DataAccessException {
		connection.disconnect();
	}

	@Test
	void testFindCustomerByPhoneNo() throws IllegalArgumentException, DataAccessException {
		//Arrange
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
	
	@Test
	void testCustomerDoesntExists() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			customerDB.findCustomerByPhoneNo("66656666", false);
			throw new IllegalArgumentException("Customer not found");
		});
		//Assert
		assertEquals("Customer not found", exceptionThrown.getMessage());
	}

}
