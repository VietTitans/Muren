package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.OrderDBIF;
import db.ResetDB;
import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;
import model.Order;
import model.StockMaterial;

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
		ResetDB.main(null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testFindAndAddCustomerByPhoneNo() throws DataAccessException {
		//Arrange
		//Customer exits in the database John, Doe, phoneNo: 1234567s john.doe@example.com
		//Act
		Customer result = orderController.findAndAddCustomerByPhoneNo("12345678");
		//Assert
		assertEquals("John", result.getfName());
		assertEquals("Doe", result.getlName());
		assertEquals("john.doe@example.com", result.getEmail());
	}

	@Test
	void testFindAndAddMaterialByMaterialNo() throws DataAccessException {
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
		//Iteration through list of HourLogs and sum hoursWorked
		BigDecimal result = new BigDecimal(0);
		for (int i = 0; i < currentOrder.getHourLogs().size(); i++) {
			result = result.add(hourLog.getHoursWorked());
		} 
		BigDecimal expectedResult = new BigDecimal(10);
		assertEquals(expectedResult, result);
	}
	//Boundary test for add Hourlog to Order
	@Test
	void testAddNegativeWorkHours() throws DataAccessException {
		//Arrange
		//Customer with phoneNo 12345678 exists in the database
		//MaterialNo 1001 exists in the database
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		BigDecimal invalidInput = new BigDecimal(-1);
		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			orderController.addWorkHours(employee, invalidInput);
			throw new Exception("Invalid amount chosen");
		});
		//Assert
		String result = exceptionThrown.getMessage();
		String expectedResult = "Invalid amount chosen"; 
		assertEquals(expectedResult, result);
	}
	
	@Test
	void testZeroWorkHours() throws DataAccessException {
		//Arrange
		//Customer with phoneNo 12345678 exists in the database
		//MaterialNo 1001 exists in the database
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		BigDecimal invalidInput = new BigDecimal(0);
		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			orderController.addWorkHours(employee, invalidInput);
			throw new Exception("Invalid amount chosen");
		});
		//Assert
		String result = exceptionThrown.getMessage();
		String expectedResult = "Invalid amount chosen"; 
		assertEquals(expectedResult, result);
	}
	@Test
	void testAddTooManyWorkHours() throws DataAccessException {
		//Arrange
		//Customer with phoneNo 12345678 exists in the database
		//MaterialNo 1001 exists in the database
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		BigDecimal invalidInput = new BigDecimal(1000);
		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			orderController.addWorkHours(employee, invalidInput);
			throw new Exception("Invalid amount chosen");
		});
		//Assert
		String result = exceptionThrown.getMessage();
		String expectedResult = "Invalid amount chosen"; 
		assertEquals(expectedResult, result);
	}
	
	//Boundary testing: Add material quantity to order
	@Test
	void testAddMaterialLogToOrderOverMaxStockLimitThrowsException() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		//Cement (materialNo 1001) has a maximum stock of 75 in database
		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			orderController.findAndAddMaterialByMaterialNo(employee, 1001, 40);
			throw new Exception("Invalid amount chosen");
		});
		//Assert
		String result = exceptionThrown.getMessage();
		String expectedResult = "Invalid amount chosen"; 
		assertEquals(expectedResult, result);
		
	}
	
	@Test
	void testAddMaterialLogToOrderWithinStockLimit() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		Order order = new Order(employee);
		StockMaterial cement = (StockMaterial)materialController.findMaterialByMaterialNo(1001);
		//Cement has available quantity of 50 in database
		MaterialLog materialLog = new MaterialLog(employee, cement, 20);
		//Act
		order.addMaterialLogToOrder(materialLog);
		//Assert;
		int result = cement.getQuantity();
		int expectedResult = 30;
		assertEquals(expectedResult, result);
		
	}
	
	@Test
	void testAddMaterialLogToOrderUnderMinStockLimit() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		Order order = new Order(employee);
		StockMaterial cement = (StockMaterial)materialController.findMaterialByMaterialNo(1001);
		//Cement has available quantity of 50 in database
		int invalidInput = 0;
		MaterialLog materialLog = new MaterialLog(employee, cement, invalidInput);
		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			order.addMaterialLogToOrder(materialLog);
			throw new IllegalArgumentException("Invalid amount chosen");
		});
		int resultQuantity = cement.getQuantity();
		int expectedQuantity = 50;
		assertEquals(expectedQuantity, resultQuantity);
		assertEquals("Invalid amount chosen", exceptionThrown.getMessage());
	}
	
	@Test
	void testAddMaterialLogToOrderNegativeInput() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		Order order = new Order(employee);
		StockMaterial cement = (StockMaterial)materialController.findMaterialByMaterialNo(1001);
		//Cement has available quantity of 50 in database
		int invalidInput = -1;
		MaterialLog materialLog = new MaterialLog(employee, cement, invalidInput);
		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			order.addMaterialLogToOrder(materialLog);
			throw new IllegalArgumentException("Invalid amount chosen");
		});
		int resultQuantity = cement.getQuantity();
		int expectedQuantity = 50;
		assertEquals(expectedQuantity, resultQuantity);
		assertEquals("Invalid amount chosen", exceptionThrown.getMessage());
	}
	
	@Test
	void testNullMaterialInputInMaterialLogThrowsException() throws DataAccessException {
		//Arrange
		Employee employee = new Employee();
		Order order = new Order(employee);
		Material invalidInput = null;
		MaterialLog materialLog = new MaterialLog(employee, invalidInput, 20);
		Exception exceptionThrown = assertThrows(Exception.class, () -> {
			//Act
			order.addMaterialLogToOrder(materialLog);
			throw new Exception("Error: Material is null");
		});
		assertEquals("Error: Material is null", exceptionThrown.getMessage());
	}
}
