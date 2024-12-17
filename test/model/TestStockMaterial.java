package model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import db.ResetDB;

public class TestStockMaterial {

	static Price salesPrice;
	static Price purchasePrice;
	static MaterialDescription materialDescription;
	
	@BeforeEach
	void setUp() throws DataAccessException {
		salesPrice = new Price(new BigDecimal(20));
		purchasePrice = new Price(new BigDecimal(17));
		materialDescription = new MaterialDescription("This is cement");

	}
	
	@AfterEach
	void tearDown() throws DataAccessException {
	}
	
	@Test
	public void testAddStockReservation() {
		//Arrange
		ArrayList<Price> salesPrices = new ArrayList<>();
		Price salesPrice = new Price(null);
		salesPrices.add(0, salesPrice);
		ArrayList<Price> purchasePrices = new ArrayList<>();
		Price purchasePrice = new Price(null);
		purchasePrices.add(0, purchasePrice);
		
		StockMaterial stockMaterial = new StockMaterial(1001, "cement", null, salesPrices, purchasePrices, 15, 75, 50);
		StockReservation stockReservation = new StockReservation(1, LocalDateTime.now());
		//Act
		stockMaterial.addStockReservation(stockReservation);
		//Assert
		assertEquals(1, stockMaterial.getStockReservations().size());
	}
	
	//Boundary testing addReservation: Over available amount
	@Test
	public void testReservationOverAvailableLimitThrowsException() {
		//Arrange
		ArrayList<Price> salesPrices = new ArrayList<>();
		Price salesPrice = new Price(null);
		salesPrices.add(0, salesPrice);
		ArrayList<Price> purchasePrices = new ArrayList<>();
		Price purchasePrice = new Price(null);
		purchasePrices.add(0, purchasePrice);
		
		int availableAmount = 50;
		StockMaterial stockMaterial = new StockMaterial(1001, "cement", null, salesPrices, purchasePrices, 15, 75, availableAmount);
		int reserveAmount = 51;
		StockReservation stockReservation = new StockReservation(reserveAmount, LocalDateTime.now());

		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			stockMaterial.addStockReservation(stockReservation);
			throw new Exception("Cannot reserve over available amount");
		});
		String result = exceptionThrown.getMessage();
		//Assert
		assertEquals("Cannot reserve over available amount", result);
		
	}
	
	//Boundary testing addReservation: Zero input
	@Test
	public void testReservationUnderAvailableLimitThrowsException() {
		//Arrange
		ArrayList<Price> salesPrices = new ArrayList<>();
		Price salesPrice = new Price(null);
		salesPrices.add(0, salesPrice);
		ArrayList<Price> purchasePrices = new ArrayList<>();
		Price purchasePrice = new Price(null);
		purchasePrices.add(0, purchasePrice);
		
		int availableAmount = 50;
		StockMaterial stockMaterial = new StockMaterial(1001, "cement", null, salesPrices, purchasePrices, 15, 75, availableAmount);
		int reserveAmount = 0;
		StockReservation stockReservation = new StockReservation(reserveAmount, LocalDateTime.now());

		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			stockMaterial.addStockReservation(stockReservation);
			throw new Exception("Cannot reserve the required amount");
		});
		String result = exceptionThrown.getMessage();
		//Assert
		assertEquals("Cannot reserve the required amount", result);
		
	}
	
	//Boundary testing addReservation: Negative input
	@Test
	public void testReservationMinusUnderAvailableLimitThrowsException() {
		//Arrange
		ArrayList<Price> salesPrices = new ArrayList<>();
		Price salesPrice = new Price(null);
		salesPrices.add(0, salesPrice);
		ArrayList<Price> purchasePrices = new ArrayList<>();
		Price purchasePrice = new Price(null);
		purchasePrices.add(0, purchasePrice);
		
		int availableAmount = 50;
		StockMaterial stockMaterial = new StockMaterial(1001, "cement", null, salesPrices, purchasePrices, 15, 75, availableAmount);
		int reserveAmount = -1;
		StockReservation stockReservation = new StockReservation(reserveAmount, LocalDateTime.now());

		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			stockMaterial.addStockReservation(stockReservation);
			throw new Exception("Cannot reserve the required amount");
		});
		//Assert
		String result = exceptionThrown.getMessage();
		String expectedResult = "Cannot reserve the required amount"; 
		assertEquals(expectedResult, result);
		
	}

	@Test
	public void testCalculateAvailableAmount() {
		//Arrange
		ArrayList<Price> salesPrices = new ArrayList<>();
		Price salesPrice = new Price(null);
		salesPrices.add(0, salesPrice);
		ArrayList<Price> purchasePrices = new ArrayList<>();
		Price purchasePrice = new Price(null);
		purchasePrices.add(0, purchasePrice);
		
		int availableAmount = 50;
		StockMaterial stockMaterial = new StockMaterial(1001, "cement", null, salesPrices, purchasePrices, 15, 75, availableAmount);
		int reserveAmount = 20;
		StockReservation stockReservation = new StockReservation(reserveAmount, LocalDateTime.now());
		//Act
		stockMaterial.addStockReservation(stockReservation);
		//Assert
		assertEquals(30, stockMaterial.calculateAvailableAmount());
	}

}
