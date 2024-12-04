package db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import controller.DataAccessException;
import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.Order;
import model.Price;

public class TryMe {

	public static void main(String[] args) throws DataAccessException, SQLException {
		Employee emp = new Employee();
		emp.setEmployeeId(1);
		Price price = new Price(BigDecimal.valueOf(1.00));
		MaterialDescription matdes = new MaterialDescription("Ting");
		Material mat =  new Material (1, "ds", matdes , price, price);
		MaterialLog mlog = new MaterialLog(emp,mat,1);
		
		MaterialLogDB db = new MaterialLogDB();
		db.saveMaterialLog(mlog, 1);
		
		HourLog hourlog = new HourLog(emp, BigDecimal.valueOf(4));
		HourLogDB hourLogDB = new HourLogDB();
		hourLogDB.saveHourLog(hourlog, 1);
		
		Order order = new Order(emp);
		Customer customer = new Customer();
		customer.setCustomerId(1);
		order.addCustomerToOrder(customer);
		
		order.addHourLogToOrder(hourlog);
		order.addMaterialLogToOrder(mlog);
		
		OrderDB orderDB = new OrderDB();
		order.setDeadLine(LocalDate.now());
		
		orderDB.saveOrder(order);
	}

}
