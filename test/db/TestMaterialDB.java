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
	
	@Test
	void testMaterialDoesntExists() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testNegativeMaterialInput() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testZeroMaterialInput() throws DataAccessException {
		//TODO:STUBS
	}
	

	
	
}
