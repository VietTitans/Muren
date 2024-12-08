package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Material;
import model.StockMaterial;

public class TestMaterialDB {
	
	static MaterialDB materialDB;
	static DBConnection connection;
	
	@BeforeAll
	void setUp() throws DataAccessException {
		materialDB = new MaterialDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
	}
	
	@AfterAll
	void tearDown() throws DataAccessException {
		connection.disconnect();
	}
	
	
	@Test
	public void testFindMaterialByProductNo() throws DataAccessException {
		//Arrange
		//No action
		//Act
		Material material = materialDB.findMaterialByProductNo(1001, true);
		StockMaterial stockMaterial = (StockMaterial) material;
		//Assert
		assertEquals("1001", stockMaterial.getProductNo());
		assertEquals("cement", stockMaterial.getProductName());
		assertEquals(15, stockMaterial.getMinStock());
		assertEquals(75, stockMaterial.getMaxStock());
		assertEquals(50, stockMaterial.getQuantity());
		assertNotNull(stockMaterial.getMaterialDescription());
		assertNotNull(stockMaterial.getCurrentSalesPrice());
		assertNotNull(stockMaterial.getCurrentPurchasePrice());
		
	}
	
	@Test
	public void testFindAndAddStockReservationByStockMaterialId() throws DataAccessException {
		//TODO: STUBS
		//Arrange
		//No action
		//Act
		//Assert
	}
	
	@Test
	public void testMaterialDoesntExists() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			materialDB.findMaterialByProductNo(6761137, true); //Searching for an invalid product
			throw new IllegalArgumentException("Material not found");
		});
		//Assert
		assertEquals("Material not found", exceptionThrown.getMessage());
	}
	
	
	@Test
	public void testStockReservationDoesntExits() throws DataAccessException {
		//Using NullPointerException to test for null
		//Arrange
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			materialDB.findAndAddStockReservationByStockMaterialId(613237, 1001); 
			throw new IllegalArgumentException("Material not found");
		});
		//Assert
		assertEquals("Material not found", exceptionThrown.getMessage());
	}
}
