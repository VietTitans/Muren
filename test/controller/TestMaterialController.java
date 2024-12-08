package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Material;

class TestMaterialController {
	private MaterialController materialController;

	@BeforeEach
	void setUp() throws Exception {
		materialController = new MaterialController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindMaterialByMaterialNo() throws Exception {
		//Arrange
		//Material(int, string) material (1001, "cement") exists in the DB:
		//Act
		int materialNo = 1001;
		Material result = materialController.findMaterialByMaterialNo(materialNo, false);
		//Assert
		String expectedResult = "cement";
		assertEquals(expectedResult, result.getProductName());
	}
	
	@Test
	void testMaterialDoesntExists() throws NullPointerException {
		//Arrange
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			int invalidMaterialNo = 0005;
			materialController.findMaterialByMaterialNo(invalidMaterialNo, false);
			throw new NullPointerException("Material not found");
		});
		//Assert
		assertEquals("Material not found", exceptionThrown.getMessage());
	}
	

}
