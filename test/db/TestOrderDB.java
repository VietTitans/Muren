package db;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.GeneralException;
import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.Order;
import model.Price;

class TestOrderDB {

	static OrderDB orderDB;
	private DBConnection connection;
	
	@BeforeEach
	void setUp() throws Exception {
		//Resets the database to a known state 
		ResetDB.main(null);
		orderDB = new OrderDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		connection.disconnect();
	}

	@Test
	void testSaveOrderToDatabaseMainsuccess() throws DataAccessException, SQLException, GeneralException {
		//Arrange
		Employee emp = new Employee();
		emp.setEmployeeId(1);
		Order order = new Order(emp);
		Price price = new Price(BigDecimal.valueOf(1.00));
		MaterialDescription materialDescription = new MaterialDescription("Ting");
		Material material =  new Material (1001, "ds", materialDescription , price, price);
		Customer customer = new Customer();
		customer.setCustomerId(1);
		MaterialLog materialLog = new MaterialLog(emp,material,1);
		
		MaterialLogDB materialLogDB = new MaterialLogDB();
		materialLogDB.saveMaterialLog(materialLog, 1);
		
		HourLog hourlog = new HourLog(emp, BigDecimal.valueOf(1));
		HourLogDB hourLogDB = new HourLogDB();
		hourLogDB.saveHourLog(hourlog, 1);
		
		order.addCustomerToOrder(customer);
		
		order.addHourLogToOrder(hourlog);
		order.addMaterialLogToOrder(materialLog);
		
		OrderDB orderDB = new OrderDB();
		order.setDeadLine(LocalDate.now());
		
		int returnedKey = orderDB.saveOrder(order);
	
	//Assert
	assertEquals(6,returnedKey);
	
	}
	@Test
	void SaveWithInvalidinput() throws DataAccessException, SQLException {
		Employee employee = new Employee();
		employee.setEmployeeId(1000);
		Order order = new Order(employee);
		OrderDB orderDB = new OrderDB();

		
		NullPointerException exceptionThrown = assertThrows(NullPointerException.class, () -> {
			//Act
			orderDB.saveOrder(order);
			throw new IllegalArgumentException("Customer not found");
		});
	assertEquals("java.lang.NullPointerException:",exceptionThrown);
	}

}
