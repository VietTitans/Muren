package db;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Employee;
import model.HourLog;

class TestHourLogDB {

	static HourLogDB hourLogDB;

	@BeforeAll
	static void setUp() throws Exception {
		hourLogDB = new HourLogDB();
		
	}

	@BeforeEach
	void tearDown() throws DataAccessException {
		ResetDB.main(null);
	}

	@Test
	void testSaveHourLog() throws DataAccessException {
		//Arrange
		int expectedHourLogId = 6;
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		BigDecimal workedHours = new BigDecimal("2.0");
		HourLog hourLog = new HourLog(employee, workedHours);
		int orderId = 1; 
		//Act
		int returnedKey = hourLogDB.saveHourLog(hourLog, orderId);
		//Assert
		//TODO: Implement getHourLog()
		assertEquals(expectedHourLogId, returnedKey);
	}
	
	@Test
	void testSaveHourLogDoesntExist() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		int orderId = 665;
		Exception exceptionThrown = assertThrows(Exception .class, () -> {
			//Act
			//TODO: Implement getHourLog()
//			hourLogDB.getHourLog(orderId);
			throw new IllegalArgumentException("Hourlog not found");
		});
		//Assert
		assertEquals("Hourlog not found", exceptionThrown.getMessage());
	}

}
