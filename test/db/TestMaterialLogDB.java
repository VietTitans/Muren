package db;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;

class TestMaterialLogDB {

	static MaterialLogDB materialLogDB;
	private DBConnection connection;

	@BeforeAll
	void setUp() throws Exception {
		materialLogDB = new MaterialLogDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
		
	}

	@AfterAll
	void tearDown() throws DataAccessException {
		connection.disconnect();
	}

	
	@Test
	void testSaveHourLog() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		Material material = new Material(1, "Spand");
		int orderId = 1; 
		MaterialLog expectedResult = new MaterialLog(employee, material, orderId);
		//Act
		materialLogDB.saveMaterialLog(expectedResult, orderId);
		//Assert
		//TODO: Implement getMaterialLog()
		HourLog result = materialLogDB.getMaterialLog(orderId);
		assertEquals(expectedResult, result);
	}
	
	@Test
	void testSaveHourLogDoesntExist() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		int orderId = 665;
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act 
			//TODO: Implement getMaterialLog()
			materialLogDB.getHourLog(orderId);
			throw new IllegalArgumentException("Material log not found");
		});
		//Assert
		assertEquals("Material log not found", exceptionThrown.getMessage());
	}
	

}
