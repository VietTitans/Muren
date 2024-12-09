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
		StockMaterial stockMaterial = (StockMaterial) material;
		//Assert
		assertEquals("1001", stockMaterial.getMaterialNo());
		assertEquals("cement", stockMaterial.getProductName());
		assertEquals(15, stockMaterial.getMinStock());
		assertEquals(75, stockMaterial.getMaxStock());
		assertEquals(50, stockMaterial.getQuantity());
		assertNotNull(stockMaterial.getMaterialDescription());
		assertNotNull(stockMaterial.getCurrentSalesPrice());
		assertNotNull(stockMaterial.getCurrentPurchasePrice());
		
	}
	
	@Test
	public void testMaterialDoesntExists() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//forkert metode navn findMaterialByMaterialNo
			//Act
			materialDB.findMaterialByMaterialNo(6761137); //Searching for an invalid product
			throw new IllegalArgumentException("Material not found");
		});
		//Assert
		assertEquals("Material not found", exceptionThrown.getMessage());
	}
	
}
