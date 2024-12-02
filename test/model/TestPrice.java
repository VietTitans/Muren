package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TestPrice {

	@Test
	void testPriceConstructor() {
		//Arrange
		
		
		//Act
		Price price = new Price(20);
		
		//Assert
		assertNotNull(price);
		
		
	}
	
	@Test
	void testPriceConstructor2() {
		//Arrange
		
		
		//Act
		Price price = new Price(LocalDateTime.of(2024, 12, 25, 12, 0), 20);
		
		//Assert
		assertNotNull(price);
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void testGetPreVATValue() {
		//Arrange
		Price price = new Price(20);
		
		//Act
		double value = price.getPreVATValue();
		
		//Assert
		assertEquals(20, value);
		
		
	}
	
	@Test
	void testGetTimeStamp() {
		//Arrange
		Price price = new Price(LocalDateTime.of(2024, 12, 25, 12, 0), 20);
		
		//Act
		LocalDateTime timeStamp = price.getTimeStamp();
		
		//Assert
		assertEquals(LocalDateTime.of(2024, 12, 25, 12, 0), timeStamp);
		
		
	}
	
	
	
	
}
