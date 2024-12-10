package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

import controller.DataAccessException;
import model.Material;
import model.MaterialDescription;
import model.Price;

public class TestMaterialDB {
	
	static MaterialDB materialDB;
	
	@BeforeAll
	static void setUp() throws DataAccessException {
		materialDB = new MaterialDB();
		
	}
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		// Resets the database to a known state
		ResetDB.main(null);
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
		//Arrange
		Exception exceptionThrown = assertThrows(Exception .class, () -> {
			//Act
			materialDB.findMaterialByMaterialNo(6761137); //Searching for an invalid product
			throw new IllegalArgumentException("Material not found");
		});
		//Assert
		assertEquals("Material not found", exceptionThrown.getMessage());
	}
	
	@Test
	public void testInsertIntoSalesPrice() throws DataAccessException, SQLException {
		//Arrange
		Material material = materialDB.findMaterialByMaterialNo(1001);
		Price newSalesPrice = new Price(LocalDateTime.of(2024, 12, 10, 12, 0), new BigDecimal(150.00));
		//Act
		materialDB.insertNewSalesPrice(material.getMaterialNo(), newSalesPrice);
		
		//Assert
		
	}
	
	@Test
	public void testInsertIntoPurchasePrice() throws DataAccessException, SQLException {
		//Arrange
		Material material = materialDB.findMaterialByMaterialNo(1001);
		Price newPurchasePrice = new Price(LocalDateTime.of(2024, 12, 10, 12, 0), new BigDecimal(180.00));
		//Act
		materialDB.insertNewPurchasePrice(material.getMaterialNo(), newPurchasePrice);
		//Assert
				
	}
	
	@Test
	public void testInsertIntoMaterialDescription() throws DataAccessException, SQLException {
		//Arrange
		Material material = materialDB.findMaterialByMaterialNo(1001);
		MaterialDescription newMaterialDescription = new MaterialDescription(LocalDateTime.of(2024, 12, 10, 12, 0), "This is a test");
		//Act
		materialDB.insertNewMaterialDescription(material.getMaterialNo(), newMaterialDescription);	
		//Assert
				
	}
	
}
