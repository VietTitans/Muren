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
	public void testFindMaterialByProductNo() throws DataAccessException {
		//Arrange
		//No action
		//Act
		Material material = materialDB.findMaterialByMaterialNo(1001);
		//Assert
		assertNotNull(material);
		
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
