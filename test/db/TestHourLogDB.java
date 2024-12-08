package db;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Employee;
import model.HourLog;

class TestHourLogDB {

	static HourLogDB hourLogDB;

	@BeforeAll
	void setUp() throws Exception {
		hourLogDB = new HourLogDB();
		
	}

	@AfterAll
	void tearDown() throws DataAccessException {
	}

	@Test
	void testSaveHourLog() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		BigDecimal workedHours = new BigDecimal("2.0");
		HourLog expectedResult = new HourLog(employee, workedHours);
		int orderId = 1; 
		//Act
		hourLogDB.saveHourLog(expectedResult, orderId);
		//Assert
		//TODO: Implement getHourLog()
		HourLog result = hourLogDB.getHourLog(orderId);
		assertEquals(expectedResult, result);
	}
	
	@Test
	void testSaveHourLogDoesntExist() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		int orderId = 665;
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			//TODO: Implement getHourLog()
			hourLogDB.getHourLog(orderId);
			throw new IllegalArgumentException("Hourlog not found");
		});
		//Assert
		assertEquals("Hourlog not found", exceptionThrown.getMessage());
	}

}
