package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOrderController {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	//Inventory: Boundry testing
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
	
	
	//Material input: Boundry testing
	@Test
	void testZeroMaterialInput() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testValidMaterialInput() throws DataAccessException {
		//TODO:STUBS
	}

	@Test
	void testNegativeMaterialInput() throws DataAccessException {
		//TODO:STUBS
	}
	
}
