package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Customer;

class TestCustomerDB {

	static CustomerDB customerDB;
	private DBConnection connection;

	@BeforeEach
	void setUp() throws Exception {
		customerDB = new CustomerDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
		
		//Customers that exits in CustomerDB
//		('John', 'Doe', '12345678', 'john.doe@example.com', 1),
//		('Jane', 'Smith', '22222222', 'jane.smith@example.com', 2),
//		('Emily', 'Brown', '71024388', 'emily.brown@example.com', 3),
//		('Michael', 'Johnson', '20318107', 'michael.johnson@example.com', 4),
//		('Sarah', 'Davis', '47530941', 'sarah.davis@example.com', 5),
//		('Aaron', 'Smith', '88551906', 'aaron.smith@example.com', 6),
//		('Jeremy', 'Jones', '78123194', 'jeremy.jones@example.com', 7),
//		('William', 'Lee', '19532742', 'willaim.lee@example.com', 8),
//		('Michael', 'Scott', '91629532', 'michael.scott@example.com', 9);
//		('Michael', 'Johnson', '20318107', 'michael.johnson@example.com', 4),
//		('Sarah', 'Davis', '47530941', 'sarah.davis@example.com', 5),
//		('Aaron', 'Smith', '88551906', 'aaron.smith@example.com', 6),
//		('Jeremy', 'Jones', '78123194', 'jeremy.jones@example.com', 7),
//		('William', 'Lee', '19532742', 'willaim.lee@example.com', 8),
//		('Michael', 'Scott', '91629532', 'michael.scott@example.com', 9);
		
	}

	@AfterEach
	void tearDown() throws Exception {
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
		assertEquals(expectedResult, result);
		
	}

}
