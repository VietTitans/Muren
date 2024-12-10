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

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindEmployeeByEmployeeId() throws GeneralException, DataAccessException {
		//Arrange
		/*Employee exists in the database
		Michael Scott 
		CPR: 1234567890	
		personId: 9
		*/
		//Act
		int validEmployeeId = 1;
		Employee result = employeeController.findEmployeeByEmployeeId(validEmployeeId, false);
		//Assert
		String expectedFName = "Michael";
		String expectedLName = "Scott";
		String expectedCPR = "1234567890";
		int expectedEmployeeId = 1;
		assertEquals(expectedFName, result.getfName());
		assertEquals(expectedLName, result.getlName());
		assertEquals(expectedCPR, result.getCpr());
		assertEquals(expectedEmployeeId, result.getEmployeeId());
	}
	
	@Test
	void testEmployeeDoesntExists() throws NullPointerException {
		//Arrange
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			int invalidEmployeeId = 9;
			employeeController.findEmployeeByEmployeeId(invalidEmployeeId, false);
			throw new NullPointerException("Employee not found");
		});
		//Assert
		assertEquals("Employee not found", exceptionThrown.getMessage());
	}


}
