package db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.GeneralException;
import model.Employee;

class TestEmployeeDB {
	
	static EmployeeDB employeeDB; 

	@BeforeEach
	void setup() throws SQLException, DataAccessException {
		//Resets the database to a known state 
		ResetDB.main(null);
		employeeDB = new EmployeeDB();
	}
	
	@AfterEach
	void tearDown() throws DataAccessException {
	}
	
	@Test
	void testFindEmployeeByEmployeeId() throws SQLException, DataAccessException, GeneralException {
		//Arrange 
		Employee expectedEmployee = new Employee();
		expectedEmployee.setfName("Michael");
		expectedEmployee.setlName("Scott");
		expectedEmployee.setPhoneNo("91629532");
		expectedEmployee.setEmail("michael.scott@example.com");
		expectedEmployee.setEmployeeId(1);
		expectedEmployee.setAddress(null);
		expectedEmployee.setCpr("1234567890");
				
		//Act 
		Employee foundEmployee = employeeDB.findEmployeeByEmployeeId(1, false);
		
		//Assert
		assertEquals("Michael",foundEmployee.getfName());
		assertEquals("Scott",foundEmployee.getlName());
		assertEquals("91629532",foundEmployee.getPhoneNo());
		assertEquals("michael.scott@example.com",foundEmployee.getEmail());
		assertEquals(1,foundEmployee.getEmployeeId());
		assertEquals("1234567890",foundEmployee.getCpr());
		
	}
	
	@Test
	void testEmployeeDoesntExists() throws DataAccessException { 
		//Using NullPointerException to test for null
		//Arrange
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			employeeDB.findEmployeeByEmployeeId(6662236, false); //Input invalid employeeId
			throw new IllegalArgumentException("Employee not found");
		});
		//Assert
		assertEquals("Employee not found", exceptionThrown.getMessage());
		
	}

}
