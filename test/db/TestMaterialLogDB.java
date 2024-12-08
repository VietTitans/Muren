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

	@BeforeAll
	void setUp() throws Exception {
		materialLogDB = new MaterialLogDB();
	}

	@AfterAll
	void tearDown() throws DataAccessException {
	}

	
	@Test
	void testSaveMaterialLog() throws DataAccessException {
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
	void testMaterialLogDoesntExist() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		int orderId = 665;
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act 
			//TODO: Implement getMaterialLog()
			materialLogDB.getMaterialLog(orderId);
			throw new IllegalArgumentException("Material log not found");
		});
		//Assert
		assertEquals("Material log not found", exceptionThrown.getMessage());
	}
	

}
