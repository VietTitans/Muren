package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.OrderDBIF;
import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.Order;

class TestOrderController {
	private OrderController orderController;
	private MaterialController materialController;
	private EmployeeController employeeController;
	private LogController logController;
	private OrderDBIF orderInterface;

	@BeforeEach
	void setUp() throws Exception {
		orderController = new OrderController();
		materialController = new MaterialController();
		employeeController = new EmployeeController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
//	@Test
//	void testFindAndAddCustomerByPhoneNo() throws DataAccessException {
//		//Arrange
//		//Customer exits in the database John, Doe, phoneNo: 12345678, john.doe@example.com
//		//Act
//		Customer result = orderController.findAndAddCustomerByPhoneNo("12345678");
//		//Assert
//		assertEquals("John", result.getfName());
//		assertEquals("Doe", result.getlName());
//		assertEquals("john.doe@example.com", result.getEmail());
//	}
//	
//	
//	@Test
//	void testFindAndAddMaterialByMaterialNo() throws DataAccessException {
//		//TODO:STUBS
//		//Arrange
//		//Material Cement with productNo 1001 exists in the DB
//		//Act
//		Material foundMaterial = materialController.findMaterialByMaterialNo(1001);
//		//Assert
//		String expextedResult = "Cement";
//		String result = foundMaterial.getProductName();
//		assertEquals(expextedResult, result);
//	}
//	
//	@Test
//	void testFindEmployeeByEmployeeId() throws DataAccessException, GeneralException {
//		//TODO:STUBS
//		//Arrange
//		//Employee with CPR: 1234567890, employeeNo: 1 exists in the DB
//		//Act
//		employeeController.findEmployeeByEmployeeId(1, false);
//		//Assert
//	}
	
	@Test
	void testAddWorkHours() throws DataAccessException {
		//Arrange
		//Create random Employee
		Employee employee = new Employee();
		employee.setCpr("9244"); 
		//Set worked hours
		BigDecimal workedHours = new BigDecimal(10); 
		HourLog hourLog = new HourLog(employee, workedHours);
		//Create order
		Order currentOrder = new Order(employee);
		//Act
		currentOrder.addHourLogToOrder(hourLog);
		//Assert
		BigDecimal expectedResult = new BigDecimal(10);
		BigDecimal result = new BigDecimal(0);
		//Iteration through list of HourLogs and sum hoursWorked
		for (int i = 0; i < currentOrder.getHourLogs().size(); i++) {
			result = result.add(hourLog.getHoursWorked());
		} 
		assertEquals(expectedResult, result);
	}
	
	@Test
	void testSaveOrder() throws DataAccessException, GeneralException {
		//Arrange
		Employee employee = new Employee();
		employee.setCpr("9244"); 
		Order order = new Order(employee);
		//Act
		orderInterface.saveOrder(order);
		//Assert
		//TODO: How to test this?
	}
	
	/*
	//Inventory: Boundry testing
	@Test
	void testOverMaxStockLimit() throws DataAccessException {
		//TODO:STUBS
		 //Arrange
		 //Act
		 //Assert
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
