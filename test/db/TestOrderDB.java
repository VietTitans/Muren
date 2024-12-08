package db;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
import model.StockMaterial;

class TestOrderDB {

	static OrderDB orderDB;

	@BeforeEach
	void setUpBeforeEach() throws Exception {
		// Resets the database to a known state
		ResetDB.main(null);
	}

	@Test
	void testSaveOrder() throws DataAccessException, SQLException, GeneralException {
		// Arrange
		Customer customer = new Customer();
		customer.setCustomerId(1);
		
		Employee emp = new Employee();
		emp.setEmployeeId(1);
		
		Price purchasePrice = new Price(BigDecimal.valueOf(1.00));
		Price salesPrice = new Price(BigDecimal.valueOf(2.00));
		ArrayList<Price> purchasePrices = new ArrayList<>();
		purchasePrices.add(purchasePrice);
		ArrayList<Price> salesPrices = new ArrayList<>();
		salesPrices.add(salesPrice);
		
		MaterialDescription materialDescription = new MaterialDescription("Ting");
		MaterialLogDB materialLogDB = new MaterialLogDB();
		StockMaterial material = new StockMaterial(1001, null, materialDescription, salesPrices, purchasePrices, 0, 0, 0);
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

}
