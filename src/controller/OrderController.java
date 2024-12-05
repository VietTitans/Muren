package controller;

import model.Customer;
import model.Employee;
import model.HourLog;
import model.Material;
import model.MaterialLog;
import model.Order;
import db.OrderDBIF;

import java.math.BigDecimal;
import java.sql.SQLException;

import db.OrderDB;

public class OrderController {
	private Order currentOrder;
	private LogController logController;
	private EmployeeController employeeController;
	private MaterialController materialController;
	private OrderDBIF orderInterface;

	
	public OrderController() throws DataAccessException, SQLException {
		logController = new LogController();
		employeeController = new EmployeeController();
		materialController = new MaterialController();
	}
	
	
	public void registerOrder(Employee employee) {
		currentOrder = new Order(employee);	
	}
	
	public Customer findAndAddCustomerByPhoneNo(String phoneNo) throws DataAccessException {
		CustomerController customerController = new CustomerController();
		Customer foundCustomer =  customerController.findCustomerByPhoneNo(phoneNo, false);
		currentOrder.addCustomerToOrder(foundCustomer);
		return foundCustomer;
	}
	
	public Material findAndAddMaterialByMaterialNo(Employee employee, int materialNo, int quantity) throws DataAccessException {
		Material foundMaterial = materialController.findMaterialByMaterialNo(materialNo, false);
		if (employee != null && foundMaterial != null && quantity != 0){
		MaterialLog newLog = logController.addMaterialToLog(employee, foundMaterial, quantity);
		currentOrder.addMaterialLogToOrder(newLog);
	}
		return foundMaterial;
	}
	
	public Employee findEmployeeByEmployeeId(int employeeId, boolean fullAssertion) throws GeneralException, DataAccessException {
		Employee employee = employeeController.findEmployeeByEmployeeId(employeeId, fullAssertion);
		return employee;
	}
	
	public void addWorkHours(Employee employee, BigDecimal hours) {
		if(employee != null && hours != null) {
		HourLog newLog = logController.addEmployeeToHourLog(employee, hours);
		currentOrder.addHourLogToOrder(newLog);	
		}
	}
	
	public void saveOrder() throws GeneralException, DataAccessException {
		orderInterface.saveOrder(currentOrder);
		
		
	}

}