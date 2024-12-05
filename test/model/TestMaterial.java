package model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import db.DBConnection;
import db.MaterialDB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

public class TestMaterial {

	static Price salesPrice;
	static Price purchasePrice;
	static MaterialDescription materialDescription;
	static MaterialDB materialDB;
	static DBConnection connection;
	
	@BeforeAll
	public static void setUp() throws DataAccessException {
		materialDB = new MaterialDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
		salesPrice = new Price(new BigDecimal(20));
		purchasePrice = new Price(new BigDecimal(17));
		materialDescription = new MaterialDescription("This is cement");
	
	}
	
	@AfterAll
	void tearDown() throws DataAccessException {
		connection.disconnect();
	}
	
	@Test
	public void testMaterialConstructor() {
		//Arrange
		
		//Act
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		//Assert
		assertNotNull(cement);
	}
	
	@Test
	public void testGetProductNo() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		//Act
		cement.getProductNo();
		//Assert
		assertEquals(123, cement.getProductNo());
	}
	
	@Test
	public void testGetProductName() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		//Act
		cement.getProductName();
		//Assert
		assertEquals("cement", cement.getProductName());
	}
	
	@Test
	public void testGetSalesPriceNow() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		//Act
		BigDecimal salesPrice = cement.getSalesPriceNow();
		BigDecimal actualPrice = new BigDecimal(20);
		//Assert
		assertEquals(actualPrice, salesPrice);
	}
	
	@Test
	public void testGetpurchasePriceNow() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		//Act
		BigDecimal purchasePrice = cement.getPurchasePriceNow();
		BigDecimal actualPrice = new BigDecimal(17);
		//Assert
		assertEquals(actualPrice, purchasePrice);
	}
	
	@Test
	public void testGetMaterialDescriptionNow() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		//Act
		String description = cement.getMaterialDescriptionNow();
		//Assert
		assertEquals("This is cement", description);
	}
	
	@Test
	void testMaterialDoesntExists() throws DataAccessException {
		//TODO:STUBS
	}
	
}
