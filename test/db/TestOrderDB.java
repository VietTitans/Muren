package db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;
import model.Order;
import model.Price;

class TestOrderDB {

	static OrderDB orderDB;

	@BeforeEach
	void setUpBeforeEach() throws Exception {
		// Resets the database to a known state
		ResetDB.main(null);
	}

	@Test
	void testSaveOrder() throws DataAccessException, SQLException {
		// Arrange
		Customer customer = new Customer();
		customer.setCustomerNo(1);
		
		Employee emp = new Employee();
		emp.setEmployeeId(1);
		
		Price purchasePrice = new Price(BigDecimal.valueOf(1.00));
		Price salesPrice = new Price(BigDecimal.valueOf(2.00));
		ArrayList<Price> purchasePrices = new ArrayList<>();
		purchasePrices.add(purchasePrice);
		ArrayList<Price> salesPrices = new ArrayList<>();
		salesPrices.add(salesPrice);
		
		MaterialDB materialDB = new MaterialDB();
		MaterialLogDB materialLogDB = new MaterialLogDB();
		Material material = materialDB.findMaterialByMaterialNo(1001);
		MaterialLog materialLog = new MaterialLog(emp, material, 1);
		materialLogDB.saveMaterialLog(materialLog, 1);

		HourLog hourlog = new HourLog(emp, BigDecimal.valueOf(1));
		HourLogDB hourLogDB = new HourLogDB();
		hourLogDB.saveHourLog(hourlog, 1);
		
		Order order = new Order(emp);
		order.addCustomerToOrder(customer);
		order.addHourLogToOrder(hourlog);
		order.addMaterialLogToOrder(materialLog);

		OrderDB orderDB = new OrderDB();
		order.setDeadLine(LocalDate.now());
		// Act
		int returnedKey = orderDB.saveOrder(order);
		// Assert
		assertEquals(6, returnedKey);
	}
//	
	@Test
	void testFindOrderByOrderNo() throws DataAccessException, SQLException {
		OrderDB orderDB = new OrderDB();
		Order foundOrder = orderDB.findOrderByOrderNo(1, true);
		
		assertNotNull(foundOrder);
		
	}

}
