package model;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class TestStockReservation {

	@Test
	public void testStockReservationConstructor() {
		//Arrange
		
		
		//Act
		StockReservation stockReservation = new StockReservation(20, LocalDateTime.now(), 7, 7, false);
		
		//Assert
		assertNotNull(stockReservation);
		
		
	}
	
	
}
