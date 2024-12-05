package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Material;
import model.StockMaterial;

public class TestMaterialDB {

	
	
	static MaterialDB materialDB;
	private DBConnection connection;
	
	
	
	@BeforeEach
	void setUp() throws DataAccessException {
		materialDB = new MaterialDB();
		
	}
	
	
	@AfterEach
	void tearDown() throws Exception {
		connection.disconnect();
	}
	
	@Test
	public void testFindMaterialByProductNo() throws DataAccessException {
		//Arrange
		
		
		//Act
		Material material = materialDB.findMaterialByProductNo(1001, true);
		StockMaterial stockMaterial = (StockMaterial) material;
		
		//Assert
		assertEquals("1001", stockMaterial.getProductNo());
		assertEquals("cement", stockMaterial.getProductName());
		assertEquals(15, stockMaterial.getMinStock());
		assertEquals(75, stockMaterial.getMaxStock());
		assertEquals(50, stockMaterial.getQuantity());
		assertNotNull(stockMaterial.getMaterialDescriptionNow());
		assertNotNull(stockMaterial.getSalesPriceNow());
		assertNotNull(stockMaterial.getPurchasePriceNow());
		
		
		
	}
	
	
}
