package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Material;
import model.StockMaterial;


public class TestMaterialDB {
	
	static MaterialDB materialDB;
	
	@BeforeAll
	static void setUp() throws DataAccessException {
		materialDB = new MaterialDB();
		
	}
	
	@Test
	public void testFindMaterialByMaterialNo() throws DataAccessException {
		//Arrange
		//Cement with materialNo 1001 exits in database
		//Act
		Material cement = materialDB.findMaterialByMaterialNo(1001);
		//Assert
		String result = cement.getProductName();
		String expectedResult = "Cement";
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testMaterialDoesntExists() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		Exception exceptionThrown = assertThrows(Exception .class, () -> {
			//Act
			materialDB.findMaterialByMaterialNo(6761137); //Searching for an invalid product
			throw new IllegalArgumentException("Material not found");
		});
		//Assert
		assertEquals("Material not found", exceptionThrown.getMessage());
	}
	
}
