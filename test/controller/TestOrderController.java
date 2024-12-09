package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.Employee;
import model.Material;

class TestOrderController {
	private OrderController orderController;
	private MaterialController materialController;

	@BeforeEach
	void setUp() throws Exception {
		orderController = new OrderController();
		materialController = new MaterialController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Test
	void testFindAndAddCustomerByPhoneNo() throws DataAccessException {
		//Arrange
		//Customer exits in the database John, Doe, phoneNo: 12345678, john.doe@example.com
		//Act
		Customer result = orderController.findAndAddCustomerByPhoneNo("12345678");
		//Assert
		assertEquals("John", result.getfName());
		assertEquals("Doe", result.getlName());
		assertEquals("john.doe@example.com", result.getEmail());
	}
	
	
	@Test
	void testFindAndAddMaterialByMaterialNo() throws DataAccessException {
		//TODO:STUBS
		//Arrange
		//Material Cement with productNo 1001 exists in the DB
		//Act
		Material foundMaterial = materialController.findMaterialByMaterialNo(1001);
		//Assert
		String expextedResult = "Cement";
		String result = foundMaterial.getProductName();
		assertEquals(expextedResult, result);
	}
	
	@Test
	void testFindEmployeeByEmployeeId() throws DataAccessException {
		//TODO:STUBS
		//Arrange
		//Employee with CPR: 1234567890, employeeNo: 1 exists in the DB
		//Act
		employee.
		//Assert
	}
	
	/*
	@Test
	void testAddWorkHours() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testSaveOrder() throws DataAccessException {
		//TODO:STUBS
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
	*/
}
