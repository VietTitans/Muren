package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.ResetDB;
import model.Material;

class TestMaterialController {
	private MaterialController materialController;

	@BeforeEach
	void setUp() throws Exception {
		materialController = new MaterialController();
		ResetDB.main(null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindMaterialByMaterialNo() throws Exception {
		//Arrange
		//Act
		int materialNo = 1001;
		Material result = materialController.findMaterialByMaterialNo(materialNo);
		//Assert
		String expectedResult = "Cement";
		assertEquals(expectedResult, result.getProductName());
	}
	
	@Test
	void testMaterialDoesntExists() throws  DataAccessException {
		//Arrange
		int invalidMaterialNo = 0005;
			//Act
			Material material = materialController.findMaterialByMaterialNo(invalidMaterialNo);
		//Assert
			assertNull(material);
	}
	

}
