package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import db.DBConnection;
import db.MaterialDB;

public class TestStockMaterial {

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
	public void testStockMaterialConstructor() {
		//Arrange
		
		//Act
		StockMaterial stockMaterial = new StockMaterial(123, "cement", materialDescription, purchasePrice, salesPrice, 15, 75, 30);
		//Assert
		assertNotNull(stockMaterial);
	}
	
	@Test
	public void testAddStockReservation() {
		//Arrange
		StockMaterial stockMaterial = new StockMaterial(123, "cement", materialDescription, purchasePrice, salesPrice, 15, 75, 30);
		StockReservation stockReservation = new StockReservation(20, LocalDateTime.now(), 7, 7, false);
		//Act
		stockMaterial.addStockReservation(stockReservation);
		//Assert
		assertEquals(1, stockMaterial.getStockReservations().size());
	}
	
	@Test
	public void testUpdateAvailableAmount() {
		//Arrange
		StockMaterial stockMaterial = new StockMaterial(123, "cement", materialDescription, purchasePrice, salesPrice, 15, 75, 30);
		StockReservation stockReservation = new StockReservation(20, LocalDateTime.now(), 7, 7, false);
		//Act
		stockMaterial.updateAvailableAmount(stockReservation);
		//Assert
		assertEquals(10, stockMaterial.getAvailableAmount());
	}
	
	@Test
	void testOverMaxStockLimit() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testWithInStockLimit() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testUnderMinStockLimit() throws DataAccessException {
		//TODO:STUBS
	}
	
	
}
