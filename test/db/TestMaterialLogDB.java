package db;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import controller.DataAccessException;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.Price;
import model.StockMaterial;

class TestMaterialLogDB {

	static MaterialLogDB materialLogDB;

	@BeforeAll
	static void setUp() throws Exception {
		materialLogDB = new MaterialLogDB();
	}

	@AfterAll
	static void tearDown() throws DataAccessException {
	}
	
	@BeforeEach
	public void initEach() {
		ResetDB.main(null);
	}
	

	
	@Test
	void testSaveMaterialLog() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		int expectedLogId = 6;
		ArrayList<MaterialDescription> materialDescriptions = new ArrayList<>();
		ArrayList<Price> salesPrices = new ArrayList<>();
		ArrayList<Price> purchasePrices = new ArrayList<>();

		StockMaterial stockMaterial = new StockMaterial(1001 , "Spand", materialDescriptions, salesPrices, purchasePrices, 1, 5, 2);
		int orderId = 1; 
		MaterialLog expectedResult = new MaterialLog(employee, stockMaterial, orderId);
		//Act
		int returnedKey = materialLogDB.saveMaterialLog(expectedResult, orderId);
		//Assert		
		assertEquals(expectedLogId, returnedKey);
	}

//	@Test
//	void testMaterialLogDoesntExist() throws DataAccessException {
//		//Using NullPointerException to test for null
//		//Arrange
//		int orderId = 665;
//		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
//			//Act 
//			//TODO: Implement getMaterialLog()
//			materialLogDB.getMaterialLog(orderId);
//			throw new IllegalArgumentException("Material log not found");
//		});
//		//Assert
//		assertEquals("Material log not found", exceptionThrown.getMessage());
//	}
//	

}
