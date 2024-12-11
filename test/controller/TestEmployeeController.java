package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.ResetDB;
import model.Employee;

class TestEmployeeController {
	private EmployeeController employeeController;

	@BeforeEach
	void setUp() throws Exception {
		employeeController = new EmployeeController();
		ResetDB.main(null);
	}



	@Test
	void testFindEmployeeByEmployeeId() throws GeneralException, DataAccessException {
		//Arrange
		String expectedFName = "Michael";
		String expectedLName = "Scott";
		String expectedCPR = "1234567890";
		int expectedEmployeeId = 1;
		
		//Act
		int validEmployeeId = 1;
		Employee result = employeeController.findEmployeeByEmployeeId(validEmployeeId, false);
		//Assert
		assertEquals(expectedFName, result.getfName());
		assertEquals(expectedLName, result.getlName());
		assertEquals(expectedCPR, result.getCpr());
		assertEquals(expectedEmployeeId, result.getEmployeeId());
	}
	
	@Test
	void testEmployeeDoesntExists() throws  GeneralException, DataAccessException {
		//Arrange
		int invalidEmployeeId = 9;
			//Act
			Employee employee = employeeController.findEmployeeByEmployeeId(invalidEmployeeId, false);
			
			//Assert
			assertNull(employee);
		}


}
