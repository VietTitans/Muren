package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestMaterial {

	static Price salesPrice;
	static Price purchasePrice;
	static MaterialDescription materialDescription;
	
	
	@BeforeAll
	static void setUp() {
		
	salesPrice = new Price(20);
	purchasePrice = new Price(17);
	materialDescription = new MaterialDescription("This is cement");
	
	}
	
	@Test
	void testMaterialConstructor() {
		//Arrange
		
		
		//Act
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		
		//Assert
		assertNotNull(cement);
		
		
	}
	
	@Test
	void testGetProductNo() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		
		//Act
		cement.getProductNo();
		
		//Assert
		assertEquals(123, cement.getProductNo());
		
		
	}
	
	@Test
	void testGetProductName() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		
		//Act
		cement.getProductName();
		
		//Assert
		assertEquals("cement", cement.getProductName());
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void testGetSalesPriceNow() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		
		//Act
		double salesPrice = cement.getSalesPriceNow();
		
		//Assert
		
		assertEquals(20, salesPrice);
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void testGetpurchasePriceNow() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		
		//Act
		double purchasePrice = cement.getPurchasePriceNow();
		
		//Assert
		assertEquals(17, purchasePrice);
		
		
	}
	
	@Test
	void testGetMaterialDescriptionNow() {
		//Arrange
		Material cement = new Material(123, "cement", materialDescription, purchasePrice, salesPrice);
		
		//Act
		String description = cement.getMaterialDescriptionNow();
		
		//Assert
		assertEquals("This is cement", description);
		
		
	}
	
}
