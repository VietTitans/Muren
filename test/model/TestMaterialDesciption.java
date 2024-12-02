package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TestMaterialDesciption {

	@Test
	void testMaterialDescriptionConstructor() {
		//Arrange
		
		
		//Act
		MaterialDescription materialDescription = new MaterialDescription("This is cement");
		
		//Assert
		assertNotNull(materialDescription);
		
		
	}
	
	@Test
	void testMaterialDescriptionConstructor2() {
		//Arrange
		
		
		//Act
		MaterialDescription materialDescription = new MaterialDescription(LocalDateTime.of(2024, 12, 25, 12, 0), "This is cement");
		
		//Assert
		assertNotNull(materialDescription);
		
		
	}
	
	@Test
	void testGetDescription() {
		//Arrange
		MaterialDescription materialDescription = new MaterialDescription("This is cement");
		
		//Act
		String description =materialDescription.getDescription();
		
		//Assert
		assertEquals("This is cement", description);
		
		
	}
	
	@Test
	void testGetTimeStamp() {
		//Arrange
		MaterialDescription materialDescription = new MaterialDescription(LocalDateTime.of(2024, 12, 25, 12, 0), "This is cement");
		
		//Act
		LocalDateTime timeStamp = materialDescription.getTimeStamp();
		
		//Assert
		assertEquals(LocalDateTime.of(2024, 12, 25, 12, 0), timeStamp);
		
		
	}
	
}
